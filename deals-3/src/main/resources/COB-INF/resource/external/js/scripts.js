// Per aprire un popup old style
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
// Per stampare una pagina
function printpage() {
window.print();
}
// Per copiare i campi da titolo e ochiello in editing a title e description in parole chiave
function FillBilling(f) {
  if(f.billingtoo.checked == true) {
    f.item_meta_title.value = f.item_title.value;
    f.item_meta_description.value = f.item_short.value;
    /* If more fields are used, just expand the parameters
       above to include the additional fields. */
  }
}
