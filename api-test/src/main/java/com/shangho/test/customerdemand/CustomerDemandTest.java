package com.shangho.test.customerdemand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import com.shangho.blackcore.api.customerdemand.bean.ListCustomerDemandRequest;
import com.shangho.blackcore.api.customerdemand.request.DeleteCustomerDemandRequest;
import com.shangho.blackcore.api.customerdemand.request.InsertCustomerDemandRequest;
import com.shangho.blackcore.api.customerdemand.request.UpdateCustomerDemandRequest;
import com.shangho.blackcore.api.location.request.ListLocationCategoryRequest;
import com.shangho.blackcore.api.request.APIRequest;

public class CustomerDemandTest {
	final String url = "http://127.0.0.1:8080/shangho-blackcore";

	@Test
	public void testListaa() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(12);
		list.add(13);
		list.add(11);
		list.add(155);
		list.add(12);
		list.add(176);
		Collections.sort(list);

		System.out.println(new Gson().toJson(list));
		System.out.println(list.indexOf(1));
	}

	@Test
	public void testList() {
		// this.token = token;
		// this.status = status;
		// this.budgetmax = budgetmax;
		// this.budgetminimum = budgetminimum;
		// this.sqmax = sqmax;
		// this.sqminimum = sqminimum;
		// this.houseagemax = houseagemax;
		// this.houseageminimum = houseageminimum;
		// this.categories = categories;
		// this.names = names;
		// this.housepatternitemids = housepatternitemids;
		// this.locationitemids = locationitemids;
		// this.specialdemanditemids = specialdemanditemids;
		// this.designateids = designateids;

		List<String> list = new ArrayList<String>();
		// list.add("我");
		// list.add("bc");
		ListCustomerDemandRequest request = new ListCustomerDemandRequest("token", "", 14, 0, 0, 0, 0, 0, null, list,
				null, null, null, null);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/customerdemand/list");

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
		List<Integer> locationitemids = new ArrayList<Integer>();
		locationitemids.add(4);

		List<Integer> housepatternitemids = new ArrayList<Integer>();
		housepatternitemids.add(4);

		List<Integer> specialdemanditemids = new ArrayList<Integer>();
		specialdemanditemids.add(2);

		List<Integer> designatepathids = new ArrayList<Integer>();
		designatepathids.add(6);

		InsertCustomerDemandRequest request = new InsertCustomerDemandRequest("token", 2, "1", "3", 22, 2432, 2123, 23,
				32, 2, housepatternitemids, locationitemids, specialdemanditemids, designatepathids);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/customerdemand/insert");

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
		List<Integer> locationitemids = new ArrayList<Integer>();
		locationitemids.add(4);

		List<Integer> housepatternitemids = new ArrayList<Integer>();
		housepatternitemids.add(4);

		List<Integer> specialdemanditemids = new ArrayList<Integer>();
		specialdemanditemids.add(2);

		List<Integer> designatepathids = new ArrayList<Integer>();
		designatepathids.add(611);

		InsertCustomerDemandRequest request = new InsertCustomerDemandRequest("token", 2, "1", "3", 22, 2432, 2123, 23,
				32, 2, housepatternitemids, locationitemids, specialdemanditemids, designatepathids);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/customerdemand/insert");

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
		List<Integer> locationitemids = new ArrayList<Integer>();
		locationitemids.add(4);

		List<Integer> housepatternitemids = new ArrayList<Integer>();
		housepatternitemids.add(4);

		List<Integer> specialdemanditemids = new ArrayList<Integer>();
		specialdemanditemids.add(2);

		List<Integer> designatepathids = new ArrayList<Integer>();
		designatepathids.add(2);
		designatepathids.add(3);
		designatepathids.add(6);

		UpdateCustomerDemandRequest request = new UpdateCustomerDemandRequest(2, "token", 2, "1", "3", 22, 2432, 2123,
				23, 32, 2, housepatternitemids, specialdemanditemids, locationitemids, designatepathids);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/customerdemand/update");

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
		List<Integer> locationitemids = new ArrayList<Integer>();
		locationitemids.add(4);

		List<Integer> housepatternitemids = new ArrayList<Integer>();
		housepatternitemids.add(4);

		List<Integer> specialdemanditemids = new ArrayList<Integer>();
		specialdemanditemids.add(2);

		List<Integer> designatepathids = new ArrayList<Integer>();
		designatepathids.add(2);
		designatepathids.add(3);
		designatepathids.add(6);

		UpdateCustomerDemandRequest request = new UpdateCustomerDemandRequest(0, "token", 2, "1", "3", 22, 2432, 2123,
				23, 32, 2, housepatternitemids, specialdemanditemids, locationitemids, designatepathids);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/customerdemand/update");

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
		DeleteCustomerDemandRequest request = new DeleteCustomerDemandRequest("token", 1);

		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url + "/customerdemand/delete");

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
