package org.ertuo.linliqin.biz.excel;




class GroovyStringsExample1{

	 static void main(args) {
	 	
	 	String example1 = """This is a multiline
	 	string which is going to 
	 	cover a few lines then 
	 	end with a period."""
	 	
	 	println example1
	 		 		 	
		def itext = 
		"""
		This is another multiline String
		which takes up a few lines. Doesn't 
		do anything different than the previous one.
		"""

		println itext
		
		def lang = "Groovy" 
		println "Uncle man, Uncle man, I dig ${lang}."
		
		println "I dig any language with ${lang.length()} characters in its name!"
	}
}