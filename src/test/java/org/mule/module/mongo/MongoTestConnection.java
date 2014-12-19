/**
 * Copyright (c) MuleSoft, Inc. All rights reserved. http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.md file.
 */

package org.mule.module.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.mule.api.ConnectionException;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.module.mongo.api.IndexOrder;
import org.mule.module.mongo.api.MongoClient;
import org.mule.module.mongo.api.WriteConcern;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoException;


public class MongoTestConnection
{
    private MongoCloudConnector connector;

    @Before
    public void setup() throws Exception
    {
        connector = new MongoCloudConnector();
        connector.setHost("127.0.0.1");
    }

    @Test
    public void connectionIncorrectPort() throws ConnectionException
    {
    	connector.setPort(27018);
        assertTrue(!isConnected("admin","","test"));
    }
    
    @Test
    public void connectionIncorrectCredentials()
    {
    	connector.setPort(27017);
        assertTrue(!isConnected("admin","zdrgdr","test"));
    }
    
    @Test
    public void validConnection()
    {
        connector.setPort(27017);
        assertTrue(isConnected("admin","","test"));
    }
    
    private boolean isConnected(String user,String pass,String db){
    	try {
			connector.connect(user, pass, db);
		} catch (ConnectionException e) {
			return false;
		}
		return true;
    }
}
