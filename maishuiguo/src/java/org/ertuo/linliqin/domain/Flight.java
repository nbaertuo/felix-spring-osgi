// Copyright 2008 Google Inc. All Rights Reserved.
package org.ertuo.linliqin.domain;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Max Ross <maxr@google.com>
 */
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    private String orig;

    private String dest;

    public Flight(String orig, String dest) {
        this.orig = orig;
        this.dest = dest;
    }

    public Long getId() {
        return id;
    }

    public String getOrig() {
        return orig;
    }

    public String getDest() {
        return dest;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}
