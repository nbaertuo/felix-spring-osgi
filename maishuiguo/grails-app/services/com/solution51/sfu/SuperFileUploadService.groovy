package com.solution51.sfu;

import org.codehaus.groovy.grails.commons.ConfigurationHolder

/* Copyright 2009-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information please visit www.solution51.com
 * or email info@solution51.com
 * Author: Peter Delahunty
 * Email: peter.delahunty@solution51.com
 * Date: 26-May-2009
*/
class SuperFileUploadService {

	
    boolean transactional = false

    String tempUploadDirectory = null

    File getTempUploadFile(String filename) {
        return new File(getTempUploadDirectoryConfig(),filename)
    }


    File getTempUploadDirectory() {
        return new File(getTempUploadDirectoryConfig())
    }

    private String getTempUploadDirectoryConfig() {

        if (!tempUploadDirectory) {

            def sfuConfig = ConfigurationHolder.config?.sfu

            def tempDirConfig = sfuConfig.tempUploadDirectory

            if (tempDirConfig) {

                File dir = new File(tempDirConfig)
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                tempUploadDirectory = tempDirConfig
                return tempUploadDirectory
            } else {
                String userHome = System.properties['user.home']
                tempDirConfig = "${userHome}/sfu_upload"
                File dir = new File(tempDirConfig)
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                tempUploadDirectory = tempDirConfig
                return tempUploadDirectory
            }
        }
        return tempUploadDirectory
    }

    public  byte[] getBytesFromFile(File file) throws IOException { 

    	InputStream is = new FileInputStream(file); 
    	// Get the size of the file 
    	long length = file.length(); 
    	// You cannot create an array using a long type. 
    	// It needs to be an int type. 
    	// Before converting to an int type, check 
    	// to ensure that file is not larger than Integer.MAX_VALUE. 
    	if (length > Integer.MAX_VALUE) { 
    	// File is too large } 
    	// Create the byte array to hold the data 
    	byte[] bytes = new byte[(int)length]; 
    	// Read in the bytes 
    	int offset = 0; 
    	int numRead = 0; 
    	while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) 
        	{ offset += numRead; } 
    	// Ensure all the bytes have been read in 
    	if (offset < bytes.length) { 
    		throw new IOException("Could not completely read file "+file.getName()); } 
    	// Close the input stream and return bytes is.close(); 
    	return bytes; 
    	} 
    }

}
