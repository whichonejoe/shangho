package com.shangho.test.object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.shangho.blackcore.api.designatepath.request.DeleteDesignatePathRequest;
import com.shangho.blackcore.api.designatepath.request.InsertDesignatePathRequest;
import com.shangho.blackcore.api.designatepath.request.ListDesignatePathRequest;
import com.shangho.blackcore.api.designatepath.request.UpdateDesignatePathRequest;
import com.shangho.blackcore.api.request.APIRequest;

public class DesignatePathTest {
	final String url = "http://127.0.0.1:8080/shangho-blackcore";

	@Test
	public void testList() {

		List<String> cities = new ArrayList<String>();
		cities.add("03");
		List<String> townships = new ArrayList<String>();
		townships.add("04");
		List<String> villages = new ArrayList<String>();
		villages.add("55");
		List<String> streets = new ArrayList<String>();
		streets.add("6");
		List<String> names = new ArrayList<String>();
		names.add("G");
		ListDesignatePathRequest request = new ListDesignatePathRequest("token", "", cities, townships, villages,
				streets, names);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/designatepath/list");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() {

		InsertDesignatePathRequest request = new InsertDesignatePathRequest("token", "0", "1", "2", "3", "4", "5", "6",
				"7");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/designatepath/insert");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFailInsert() {
		InsertDesignatePathRequest request = new InsertDesignatePathRequest("token", "1", "", "", "", "", "", "", "");
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/designatepath/insert");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		UpdateDesignatePathRequest request = new UpdateDesignatePathRequest("token", 7, "1", "01", "02", "03", "04",
				"05", "06", null);
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/designatepath/update");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFailUpdate() {
		UpdateDesignatePathRequest request = new UpdateDesignatePathRequest("token", 4, "1", "", "", "", "", "", "",
				"");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/designatepath/update");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNotExistedIDUpdate() {
		UpdateDesignatePathRequest request = new UpdateDesignatePathRequest("token", 999, "1", "01", "02", "03", "04",
				"05", "06", null);
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/designatepath/update");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		DeleteDesignatePathRequest request = new DeleteDesignatePathRequest("token", 1);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/object/designatepath/delete");

		APIRequest enity = new APIRequest(request);
		Gson gson = new Gson();
		String json = gson.toJson(enity);
		System.out.println(json);
		try {
			StringEntity entity = new StringEntity(json, "UTF-8");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
