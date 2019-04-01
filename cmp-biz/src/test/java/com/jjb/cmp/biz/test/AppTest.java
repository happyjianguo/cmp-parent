package com.jjb.cmp.biz.test;

import com.alibaba.fastjson.JSONArray;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testAbc(){
    	JSONArray array = JSONArray.parseArray("[0,33,0.8062999999999999,1,\"C\",0],\"suggestAmt\":0.00,\"scoreElement\":[\"贷款或信用卡最近12个月最大逾期数\",\"年龄\",\"信用卡额度使用率\",\"是否有信用卡或贷款\",\"婚姻状况\",\"近6个月审批查询总数\"]");
    }
}
