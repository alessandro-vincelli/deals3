/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.reading;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.parameters.Parameters;
import org.apache.cocoon.ProcessingException;
import org.apache.cocoon.caching.CacheableProcessingComponent;
import org.apache.cocoon.environment.Context;
import org.apache.cocoon.environment.ObjectModelHelper;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.Response;
import org.apache.cocoon.environment.SourceResolver;
import org.apache.commons.lang.SystemUtils;
import org.apache.excalibur.source.SourceValidity;
import org.apache.excalibur.source.impl.validity.NOPValidity;
import org.apache.excalibur.source.impl.validity.TimeStampValidity;
import org.deals.framework.repo.RepoBinary;
import org.xml.sax.SAXException;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * The <code>ResourceReader</code> component is used to serve binary data
 * in a sitemap pipeline. It makes use of HTTP Headers to determine if
 * the requested resource should be written to the <code>OutputStream</code>
 * or if it can signal that it hasn't changed.
 *
 * <p>Configuration:
 * <dl>
 * <dt>&lt;expires&gt;</dt>
 * <dd>This parameter is optional. When specified it determines how long
 * in miliseconds the resources can be cached by any proxy or browser
 * between Cocoon and the requesting visitor. Defaults to -1.
 * </dd>
 * <dt>&lt;quick-modified-test&gt;</dt>
 * <dd>This parameter is optional. This boolean parameter controls the
 * last modified test. If set to true (default is false), only the
 * last modified of the current source is tested, but not if the
 * same source is used as last time
 * (see http://marc.theaimsgroup.com/?l=xml-cocoon-dev&m=102921894301915 )
 * </dd>
 * <dt>&lt;byte-ranges&gt;</dt>
 * <dd>This parameter is optional. This boolean parameter controls whether
 * Cocoon should support byterange requests (to allow clients to resume
 * broken/interrupted downloads).
 * Defaults to true.
 * </dl>
 *
 * <p>Default configuration:
 * <pre>
 *   &lt;expires&gt;-1&lt;/expires&gt;
 *   &lt;quick-modified-test&gt;false&lt;/quick-modified-test&gt;
 *   &lt;byte-ranges&gt;true&lt;/byte-ranges&gt;
 * </pre>
 *
 * <p>In addition to reader configuration, above parameters can be passed
 * to the reader at the time when it is used.
 *
 * @cocoon.sitemap.component.documentation
 * The <code>ResourceReader</code> component is used to serve binary data
 * in a sitemap pipeline. It makes use of HTTP Headers to determine if
 * the requested resource should be written to the <code>OutputStream</code>
 * or if it can signal that it hasn't changed.
 * @cocoon.sitemap.component.documentation.caching Yes
 *
 * @version $Id: ResourceReader.java 654677 2008-05-09 03:52:57Z joerg $
 */
public class RepoImageReader extends AbstractReader
                            implements CacheableProcessingComponent, Configurable {

    private static final boolean CONFIGURED_BYTE_RANGES_DEFAULT = true;
    private static final int CONFIGURED_BUFFER_SIZE_DEFAULT = 8192;
    private static final boolean CONFIGURED_QUICK_TEST_DEFAULT = false;
    private static final int CONFIGURED_EXPIRES_DEFAULT = -1;

    /**
     * The list of generated documents
     */
    private static final Map documents = Collections.synchronizedMap(new HashMap());

    protected long configuredExpires = CONFIGURED_EXPIRES_DEFAULT;
    protected boolean configuredQuickTest = CONFIGURED_QUICK_TEST_DEFAULT;
    protected int configuredBufferSize = CONFIGURED_BUFFER_SIZE_DEFAULT;
    protected boolean configuredByteRanges = CONFIGURED_BYTE_RANGES_DEFAULT;
    private long lastModified = System.currentTimeMillis();
    private boolean defaultCache = true;

    protected long expires;
    protected boolean quickTest;
    protected int bufferSize;
    protected boolean byteRanges;

    protected Response response;
    protected Request request;
    //protected Source inputSource;
    protected RepoBinary repoBinary;
    protected InputStream inputS;
    
    /* Start: from ImageReader */
	private static final boolean GRAYSCALE_DEFAULT = false;
	private static final boolean ENLARGE_DEFAULT = true;
	private static final boolean FIT_DEFAULT = false;

	/* See http://developer.java.sun.com/developer/bugParade/bugs/4502892.html */
	private static final boolean JVMBugFixed = SystemUtils
	.isJavaVersionAtLeast(1.4f);

	private int width;
	private int height;
	private float[] scaleColor = new float[3];
	private float[] offsetColor = new float[3];
	private float[] quality = new float[1];

	private boolean enlarge;
	private boolean fitUniform;
	private boolean usePercent;
	private RescaleOp colorFilter;
	private ColorConvertOp grayscaleFilter;
	/* Finish: from ImageReader */

    /**
     * @param bufferSize
     */
    public void setBufferSize(int bufferSize) {
        this.configuredBufferSize = bufferSize;
    }

    /**
     * This parameter is optional. This boolean parameter controls whether
     * Cocoon should support byterange requests (to allow clients to resume
     * broken/interrupted downloads).
     * Defaults to true.
     *
     * @param byteRanges
     */
    public void setByteRanges(boolean byteRanges) {
        this.configuredByteRanges = byteRanges;
    }

    /**
     * This parameter is optional. When specified it determines how long
     * in miliseconds the resources can be cached by any proxy or browser
     * between Cocoon and the requesting visitor. Defaults to -1.
     *
     * @param expires
     */
    public void setExpires(long expires) {
        this.configuredExpires = expires;
    }

    /**
     * This parameter is optional. This boolean parameter controls the
     * last modified test. If set to true (default is false), only the
     * last modified of the current source is tested, but not if the
     * same source is used as last time
     * (see http://marc.theaimsgroup.com/?l=xml-cocoon-dev&m=102921894301915 )
     *
     * @param quickTest
     */
    public void setQuickTest(boolean quickTest) {
        this.configuredQuickTest = quickTest;
    }

    /**
     * Read reader configuration
     *
     * @deprecated use property injection instead
     */
    public void configure(Configuration configuration) throws ConfigurationException {
        // VG Parameters are deprecated as of 2.2.0-Dev/2.1.6-Dev
        final Parameters parameters = Parameters.fromConfiguration(configuration);
        this.setExpires(parameters.getParameterAsLong("expires", CONFIGURED_EXPIRES_DEFAULT));
        this.setQuickTest(parameters.getParameterAsBoolean("quick-modified-test", CONFIGURED_QUICK_TEST_DEFAULT));
        this.setBufferSize(parameters.getParameterAsInteger("buffer-size", CONFIGURED_BUFFER_SIZE_DEFAULT));
        this.setByteRanges(parameters.getParameterAsBoolean("byte-ranges", CONFIGURED_BYTE_RANGES_DEFAULT));

        // Configuration has precedence over parameters.
        setExpires(configuration.getChild("expires").getValueAsLong(configuredExpires));
        setQuickTest(configuration.getChild("quick-modified-test").getValueAsBoolean(configuredQuickTest));
        setBufferSize(configuration.getChild("buffer-size").getValueAsInteger(configuredBufferSize));
        setByteRanges(configuration.getChild("byte-ranges").getValueAsBoolean(configuredByteRanges));
    }

    /**
     * Setup the reader.
     * The resource is opened to get an <code>InputStream</code>,
     * the length and the last modification date
     */
    public void setup(SourceResolver resolver, Map objectModel, String src, Parameters par)
    throws ProcessingException, SAXException, IOException {
        super.setup(resolver, objectModel, src, par);

        this.request = ObjectModelHelper.getRequest(objectModel);
        this.response = ObjectModelHelper.getResponse(objectModel);

        this.expires = par.getParameterAsLong("expires", this.configuredExpires);
        this.quickTest = par.getParameterAsBoolean("quick-modified-test", this.configuredQuickTest);
        this.bufferSize = par.getParameterAsInteger("buffer-size", this.configuredBufferSize);
        this.byteRanges = par.getParameterAsBoolean("byte-ranges", this.configuredByteRanges);
        this.quality[0] = 1;
        
        
        char lastChar;
		String tmpWidth = par.getParameter("width", "0");
		String tmpHeight = par.getParameter("height", "0");

		
		usePercent = false;
		lastChar = tmpWidth.charAt(tmpWidth.length() - 1);
		if (lastChar == '%') {
			usePercent = true;
			width = Integer.parseInt(tmpWidth.substring(0,
					tmpWidth.length() - 1));
		} else {
			width = Integer.parseInt(tmpWidth);
		}

		lastChar = tmpHeight.charAt(tmpHeight.length() - 1);
		if (lastChar == '%') {
			usePercent = true;
			height = Integer.parseInt(tmpHeight.substring(0,
					tmpHeight.length() - 1));
		} else {
			height = Integer.parseInt(tmpHeight);
		}

		this.enlarge = par.getParameterAsBoolean("allow-enlarging",
				ENLARGE_DEFAULT);
		this.fitUniform = par.getParameterAsBoolean("fit-uniform", FIT_DEFAULT);


        //try{
            //this.inputSource = resolver.resolveURI(src);
        	this.inputS = repoBinary.getBinary(src);
            
        //} catch (SourceException e) {
         //   throw SourceUtil.handle("Error during resolving of '" + src + "'.", e);
        //}
        setupHeaders();
    }

    /**
     * Setup the response headers: Accept-Ranges, Expires
     */
    protected void setupHeaders() {
        // Tell the client whether we support byte range requests or not
        if (byteRanges) {
            response.setHeader("Accept-Ranges", "bytes");
        } else {
            response.setHeader("Accept-Ranges", "none");
        }

        if (expires > 0) {
            response.setDateHeader("Expires", System.currentTimeMillis() + expires);
        } else if (expires == 0) {
            response.setDateHeader("Expires", 0);
        }
    }

    /**
     * Recyclable
     */
    /*public void recycle() {
        this.request = null;
        this.response = null;
        if (this.inputSource != null) {
            super.resolver.release(this.inputSource);
            this.inputSource = null;
        }
        super.recycle();
    }
*/
    /**
     * @return True if byte ranges support is enabled and request has range header.
     */
    protected boolean hasRanges() {
        return this.byteRanges && this.request.getHeader("Range") != null;
    }

    /**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key hashes the src
     */
    /*public Serializable getKey() {
        return inputSource.getURI();
    }
*/
    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    /*public SourceValidity getValidity() {
        if (hasRanges()) {
            // This is a byte range request so we can't use the cache, return null.
            return null;
        } else {
            return inputSource.getValidity();
        }
    }*/

    /**
     * @return the time the read source was last modified or 0 if it is not
     *         possible to detect
     */
    /*
    public long getLastModified() {
        if (hasRanges()) {
            // This is a byte range request so we can't use the cache, return null.
            return 0;
        }

        if (quickTest) {
            return inputSource.getLastModified();
        }

        final String systemId = (String) documents.get(request.getRequestURI());
        // Note: getURI() might be null in some incomplete implementations
        final String sourceURI = inputSource.getURI();
        if (systemId == null || (sourceURI != null && sourceURI.equals(systemId))) {
            return inputSource.getLastModified();
        }

        documents.remove(request.getRequestURI());
        return 0;
    }*/

    protected void processStream(InputStream inputStream) throws IOException,
			ProcessingException {

    	if (inputStream != null){
    	if (hasTransform()) {
			if (getLogger().isDebugEnabled()) {
				getLogger().debug("image " + ((width == 0) ? "?" : Integer.toString(width))	+ "x" + ((height == 0) ? "?" : Integer.toString(height))	+ " expires: " + expires);
			}

		
			try {
				byte content[] = readFully(inputStream);
				ImageIcon icon = new ImageIcon(content);
				BufferedImage original = new BufferedImage(icon.getIconWidth(),	icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
				BufferedImage currentImage = original;
				currentImage.getGraphics().drawImage(icon.getImage(), 0, 0,	null);

				if (width > 0 || height > 0) {
					double ow = icon.getImage().getWidth(null);
					double oh = icon.getImage().getHeight(null);

					if (usePercent) {
						if (width > 0) {
							width = Math.round((int) (ow * width) / 100);
						}
						if (height > 0) {
							height = Math.round((int) (oh * height) / 100);
						}
					}

					AffineTransformOp filter = new AffineTransformOp(getTransform(ow, oh, width, height), AffineTransformOp.TYPE_BILINEAR);
					WritableRaster scaledRaster = filter.createCompatibleDestRaster(currentImage.getRaster());

					filter.filter(currentImage.getRaster(), scaledRaster);

					currentImage = new BufferedImage(original.getColorModel(), scaledRaster, true, null);
				}


					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					JPEGEncodeParam p = encoder.getDefaultJPEGEncodeParam(currentImage);
					p.setQuality(this.quality[0], true);
					encoder.setJPEGEncodeParam(p);
					encoder.encode(currentImage);

					out.flush();
			} catch (ImageFormatException e) {
				throw new ProcessingException("Error reading the image. "
						+ "Note that only JPEG images are currently supported.");
			} finally {
				// Bugzilla Bug 25069, close inputStream in finally block
				// this will close inputStream even if processStream throws
				// an exception
				inputStream.close();
			}
		} else {
			try {
				InputStream is = inputStream;

				long expires = parameters.getParameterAsInteger("expires", -1);
				if (expires > 0) {
					response.setDateHeader("Expires", System.currentTimeMillis()
							+ expires);
				}
				response.setHeader("Accept-Ranges", "bytes");

				byte[] buffer = new byte[8192];
				int length;
				while ((length = is.read(buffer)) > -1) {
					out.write(buffer, 0, length);
				}
				is.close();
				out.flush();
			} catch (RuntimeException e) {
				throw e;
			}
			 finally {
					// Bugzilla Bug 25069, close inputStream in finally block
					// this will close inputStream even if processStream throws
					// an exception
					inputStream.close();
				}
			
		} 
    	} 
    	else {
			throw new IOException(
					"Deals: Problem, resource not found or Repository not working correctly");
		}

	}

    /**
	 * Generates the requested resource.
	 */
    public void generate() throws IOException, ProcessingException {
        InputStream inputStream;
        //try {
            inputStream = this.inputS;
        //} catch (SourceException e) {
         //   throw SourceUtil.handle(
          //          "Error during resolving of the input stream", e);
        //}

        // Bugzilla Bug #25069: Close inputStream in finally block.
        try {
            processStream(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        if (!quickTest) {
            // if everything is ok, add this to the list of generated documents
            // (see
            // http://marc.theaimsgroup.com/?l=xml-cocoon-dev&m=102921894301915
            // )
            documents.put(request.getRequestURI(), "");
        }
    }
    
    
    

    /**
     * Returns the mime-type of the resource in process.
     */
    public String getMimeType() {
        Context ctx = ObjectModelHelper.getContext(objectModel);
        if (ctx != null) {
            final String mimeType = ctx.getMimeType(source);
            if (mimeType != null) {
                return mimeType;
            }
        }

        return this.parameters.getParameter("content-type", super.getMimeType());

    }

	/**
	 * @return True if image transform is specified
	 */
	
    public void setRepoBinary(RepoBinary repoBinary) {
		this.repoBinary = repoBinary;
	}

	/**
     * Generate the unique key.
     * This key must be unique inside the space of this component.
     *
     * @return The generated key hashes the src
     */
    public java.io.Serializable getKey() {
        return this.source;
    }

    /**
     * Generate the validity object.
     *
     * @return The generated validity object or <code>null</code> if the
     *         component is currently not cacheable.
     */
    public SourceValidity getValidity() {
        if (this.lastModified > 0) {
            return new TimeStampValidity(this.lastModified);
        } else {
            if (this.defaultCache) {
                return NOPValidity.SHARED_INSTANCE;
            } else {
                return null;
            }
        }
    }
    /* Start method from ImageReder */
	private boolean hasTransform() {
		return width > 0 || height > 0 ;
	}

	/**
	 * Returns the affine transform that implements the scaling.
	 * The behavior is the following: if both the new width and height values
	 * are positive, the image is rescaled according to these new values and
	 * the original aspect ratio is lost.
	 * Otherwise, if one of the two parameters is zero or negative, the
	 * aspect ratio is maintained and the positive parameter indicates the
	 * scaling.
	 * If both new values are zero or negative, no scaling takes place (a unit
	 * transformation is applied).
	 */
	private AffineTransform getTransform(double ow, double oh, double nw,
			double nh) {
		double wm = 1.0d;
		double hm = 1.0d;

		if (fitUniform) {
			//
			// Compare aspect ratio of image vs. that of the "box"
			// defined by nw and nh
			//
			if (ow / oh > nw / nh) {
				nh = 0; // Original image is proportionately wider than the box,
				// so scale to fit width
			} else {
				nw = 0; // Scale to fit height
			}
		}

		if (nw > 0) {
			wm = nw / ow;
			if (nh > 0) {
				hm = nh / oh;
			} else {
				hm = wm;
			}
		} else {
			if (nh > 0) {
				hm = nh / oh;
				wm = hm;
			}
		}

		if (!enlarge) {
			if ((nw > ow && nh <= 0) || (nh > oh && nw <= 0)) {
				wm = 1.0d;
				hm = 1.0d;
			} else if (nw > ow) {
				wm = 1.0d;
			} else if (nh > oh) {
				hm = 1.0d;
			}
		}
		return new AffineTransform(wm, 0.0d, 0.0d, hm, 0.0d, 0.0d);
	}
	protected byte[] readFully(InputStream in) throws IOException {
		byte tmpbuffer[] = new byte[4096];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i;
		while (-1 != (i = in.read(tmpbuffer))) {
			baos.write(tmpbuffer, 0, i);
		}
		baos.flush();
		return baos.toByteArray();
	}
}
