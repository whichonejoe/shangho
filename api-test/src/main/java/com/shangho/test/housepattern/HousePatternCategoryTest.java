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
import com.shangho.blackcore.api.housepattern.request.DeleteHousePatternCategoryRequest;
import com.shangho.blackcore.api.housepattern.request.InsertHousePatternCategoryRequest;
import com.shangho.blackcore.api.housepattern.request.ListHousePatternCategoryRequest;
import com.shangho.blackcore.api.housepattern.request.UpdateHousePatternCategoryRequest;
import com.shangho.blackcore.api.request.APIRequest;

public class HousePatternCategoryTest {
	final String url = "http://127.0.0.1:8080/shangho-blackcore";

	@Test
	public void testCategoryList() {
		List<String> list = new ArrayList<String>();
		list.add("4");
		ListHousePatternCategoryRequest request = new ListHousePatternCategoryRequest("token", null, list, "ASC");

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/category/list");

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
	public void testCategoryInsert() {

		InsertHousePatternCategoryRequest request = new InsertHousePatternCategoryRequest("token", "2", "1", 2);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/category/insert");

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
	public void testCategoryFailInsert() {
		InsertHousePatternCategoryRequest request = new InsertHousePatternCategoryRequest("token", "name3", "3", 50);
		// InsertHousePatternCategoryRequest request = new
		// InsertHousePatternCategoryRequest("token", null, null, null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/category/insert");

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
	public void testCategoryUpdate() {
		UpdateHousePatternCategoryRequest request = new UpdateHousePatternCategoryRequest("token", 1, "name4", "0", 30);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/category/update");

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
	public void testCategoryFailUpdate() {
		UpdateHousePatternCategoryRequest request = new UpdateHousePatternCategoryRequest("token", 4, "name4", "0", 30);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/category/update");

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
	public void testCategoryNotExistedIDUpdate() {
		UpdateHousePatternCategoryRequest request = new UpdateHousePatternCategoryRequest("token", 114, "name4", "0",
				30);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/category/update");

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
	public void testCategoryDelete() {
		DeleteHousePatternCategoryRequest request = new DeleteHousePatternCategoryRequest("token", 3);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/housepattern/category/delete");

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
