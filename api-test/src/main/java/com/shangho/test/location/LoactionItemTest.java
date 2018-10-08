package com.shangho.test.location;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.google.gson.Gson;
import com.shangho.blackcore.api.location.request.DeleteLocationItemRequest;
import com.shangho.blackcore.api.location.request.InsertLocationItemRequest;
import com.shangho.blackcore.api.location.request.UpdateLocationItemRequest;
import com.shangho.blackcore.api.request.APIRequest;

public class LoactionItemTest {
	final String url = "http://127.0.0.1:8080/shangho-blackcore";

	@Test
	public void testInsert() {
		InsertLocationItemRequest request = new InsertLocationItemRequest("token", 5, 4, "namee", "1", null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/location/item/insert");

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
		InsertLocationItemRequest request = new InsertLocationItemRequest("token", 4, 6, "namee", "1", null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/location/item/insert");

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
	public void testFailStatusInsert() {
		InsertLocationItemRequest request = new InsertLocationItemRequest("token", 5, 0, "namee", "1w", null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/location/item/insert");

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
		UpdateLocationItemRequest request = new UpdateLocationItemRequest("token", 4, 4, 5, "nawwwme3", "0", "eqw3");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/location/item/update");

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
		UpdateLocationItemRequest request = new UpdateLocationItemRequest("token", 4, 4, 7, "nawwwme3", "0", "eqw3");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/location/item/update");

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
		DeleteLocationItemRequest request = new DeleteLocationItemRequest("token", 3);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/location/item/delete");

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
