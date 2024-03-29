function makePullquote()
{
	var spans = document.getElementsByTagName('span');
	for (i = 0;  i < spans.length;  i++)
	{
		if(spans[i].className == "pullme")
		{
			var pullquote = document.createElement('blockquote');
			pullquote.innerHTML = spans[i].innerHTML;
			spans[i].parentNode.insertBefore(pullquote,null);
		}
	}
}
window.onload = makePullquote;

// The class name of toggle objects which will be initially hidden
var toggleDivClassName = "toggle";
// This controls how slowly the div initially starts moving.
// Lower values case a slower initial move speed. Must be > 1
var toggleDivSpeedMultiplier = 2;
// The delay between each move.
var toggleDivDelay = 50;

// If you only want one toggle DIV to be open at any time, set this to true
var toggleDivOnlyOneOpen = false;

// An array to hold references to all toggleDiv objects on the page
var toggleDivs = new Array();
// Contains the IDs of divs currently being toggled, so you can't stop one mid-open or close
var togglingDivs = new Object();

// The initial function that handles the request to toggle a DIV
function toggleDiv(divId,action) {
	var d = document.getElementById(divId);
	if (d==null || d.tagName!="DIV" || !d.offsetHeight || togglingDivs[divId]) { return; }

	d.style.overflow = "hidden";
	if (action=="open" || (typeof(action)=="undefined" && d.style.visibility=="hidden")) {
		if (toggleDivOnlyOneOpen) {
			setTimeout("closeAllToggleDivsExcept('"+divId+"')",5);
		}
		// open it
		var originalHeight = d.offsetHeight;
		var height = 1;
		d.style.height = height+"px";
		d.style.visibility = "visible";
		d.style.position="static";
		togglingDivs[divId] = true;
		setTimeout("toggleObject('"+divId+"','open',"+originalHeight+","+height+")",toggleDivDelay);
	}
	else if (action=="close" || (typeof(action)=="undefined" && d.style.visibility=="visible")) {
		// close it
		var originalHeight = d.offsetHeight;
		var height = originalHeight;
		togglingDivs[divId] = true;
		setTimeout("toggleObject('"+divId+"','close',"+originalHeight+","+height+")",toggleDivDelay);
	}
}

// This function closes all DIVs except the given ID, for use when only one DIV should be open at any time
function closeAllToggleDivsExcept(divId) {
	for (var i=0; toggleDivs!=null && i<toggleDivs.length; i++) {
		if (toggleDivs[i].id!=divId) {
			toggleDiv(toggleDivs[i].id,'close');
		}
	}
}

// The function that is called repeatedly until the toggle is done
function toggleObject(divId, openClose, originalHeight, height) {
	var d = document.getElementById(divId);
	if (d==null || d.tagName!="DIV") { return; }

	if (openClose=="open") {
		height = height * toggleDivSpeedMultiplier;
		if (height > originalHeight) {
			d.style.height = originalHeight+"px";
			delete togglingDivs[divId];
		}
		else {
			d.style.height = height+"px";
			setTimeout("toggleObject('"+divId+"','"+openClose+"',"+originalHeight+","+height+")",toggleDivDelay);
		}
	}
	else {
		height = height * (1/toggleDivSpeedMultiplier);
		if (height <= 1) {
			d.style.position = "absolute";
			d.style.visibility = "hidden";
			d.style.height = originalHeight+"px";
			delete togglingDivs[divId];
		}
		else {
			d.style.height = height+"px";
			setTimeout("toggleObject('"+divId+"','"+openClose+"',"+originalHeight+","+height+")",toggleDivDelay);
		}
	}
}

// A function which is called onload of the window, to hide all the toggle divs initially. This is done
// so that non-JS browsers will see the divs rather than have them be hidden.
function hideToggleDivs() {
	var divs = document.getElementsByTagName("DIV");
	for (var i=0; divs!=null && i<divs.length; i++) {
		if (divs[i].className.indexOf(toggleDivClassName)>-1) {
			toggleDivs[toggleDivs.length] = divs[i];
			var s = divs[i].style;
			s.position="absolute";
			s.visibility="hidden";
		}
	}
}

// A general function to add an event handler
function addHandler(obj, evt, newhandler, captures) {
	if (obj.attachEvent) {
		obj.attachEvent('on' + evt, newhandler);
	}
	else if (obj.addEventListener) {
		obj.addEventListener(evt, newhandler, captures);
	}
	else {
		var oldhandler;
		if (oldhandler = obj['on' + evt]) {
			obj['on' + evt] = function() {
				oldhandler();
				newhandler();
			}
		}
		else {
			obj['on' + evt] = newhandler;
		}
	}
}

// Attach an onLoad handler to the window to initially hide all the toggle DIV objects.
// Only do it if sufficient JS capability is present in the browser
if (document.getElementById && document.getElementsByTagName) {
	addHandler(window,'load',hideToggleDivs,false);
}

  function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

function printpage() {
window.print();
}