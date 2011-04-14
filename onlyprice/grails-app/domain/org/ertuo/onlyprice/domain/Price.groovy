/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */

/**
 * 
 * @author mo.duanm
 * @version $Id: ddd.groovy, v 0.1 2011-4-13 обнГ05:19:04 mo.duanm Exp $
 */
package org.ertuo.onlyprice.domain



public class Price{
    def Long id;
    def String title;
    def String description;
    def Date date;

    static mapping = {
        table 'price'
        id generator:'increment', column:'id'
    }
    static constraints = {
        title(nullable:false,blank:false)
    }
}
