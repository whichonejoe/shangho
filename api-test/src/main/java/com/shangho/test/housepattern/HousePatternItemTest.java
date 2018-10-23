package com.shangho.test.housepattern;

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
import com.shangho.blackcore.api.housepattern.request.DeleteHousePatternItemRequest;
import com.shangho.blackcore.api.housepattern.request.InsertHousePatternItemRequest;
import com.shangho.blackcore.api.housepattern.request.ListHousePatternItemRequest;
import com.shangho.blackcore.api.housepattern.request.UpdateHousePatternItemRequest;
import com.shangho.blackcore.api.request.APIRequest;

public class HousePatternItemTest {
	final String url = "http://127.0.0.1:8080/shangho-blackcore";

	@Test
	public void testList() {
		List<Integer> categories = new ArrayList<Integer>();
		categories.add(1);
		categories.add(6);
		List<Integer> refers = new ArrayList<Integer>();
		refers.add(5);

		List<String> list = new ArrayList<String>();
		list.add("我");
		list.add("bc");

		ListHousePatternItemRequest request = new ListHousePatternItemRequest("token", "1", categories, list, "ASC");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/item/list");

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
		InsertHousePatternItemRequest request = new InsertHousePatternItemRequest("token", 1, "namee5", "1", 5);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/item/insert");

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
		InsertHousePatternItemRequest request = new InsertHousePatternItemRequest("token", 1, "namee5", "2", 5);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/item/insert");

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
	public void testUpdate() {
		UpdateHousePatternItemRequest request = new UpdateHousePatternItemRequest("token", 1, 1, "nawwwme1", "1", 66);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/item/update");

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
		UpdateHousePatternItemRequest request = new UpdateHousePatternItemRequest("token", 55, 1, "nawwwme1", "1", 66);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/item/update");

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
		DeleteHousePatternItemRequest request = new DeleteHousePatternItemRequest("token", 1);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/item/delete");

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
