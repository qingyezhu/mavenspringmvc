package com.wagzhu.util;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class SoapAxis2Demo {
	public static void main(String[] args) {
		SoapAxis2Demo.testRPCClient();

	}

	public static void testRPCClient() {
		try {
			final String url = "http://219.237.222.71/datacloud/services/QueryDetailDataService?wsdl";
			try {
				RPCServiceClient serviceClient = new RPCServiceClient();
				EndpointReference targetEPR = new EndpointReference(url);
				Options options = serviceClient.getOptions();
				options.setTo(targetEPR);
				options.setAction("tns:queryDetailData");
				QName qName = new QName(
						"http://metadata.connector.dataservice.at21.com/",
						"queryDetailData");
				Object[] parameters = new Object[] { "<?xml version=\"1.0\" encoding=\"UTF-8\"?><condition><sceneid>1000</sceneid><datafrom>at21</datafrom></condition>" };
				Class[] returnTypes = new Class[] { String.class };
				Object[] element = serviceClient.invokeBlocking(qName,
						parameters, returnTypes);
				System.out.println(element);

			} catch (AxisFault e) {
				e.printStackTrace();
			}

		} finally {

		}
	}
}
