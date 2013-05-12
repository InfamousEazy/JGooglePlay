package com.google.play;

import com.google.play.proto.*;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.tools.Client;
import com.google.tools.RequestInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DfeClient extends Client {

	public static final String BASE_URL = "https://android.clients.google.com/fdfe/";
	public static final String BROWSE_URL = BASE_URL + "browse";
	public static final String SUGGEST_URL = BASE_URL + "suggest";
	public static final String LIST_URL = BASE_URL + "list";
	public static final String BULK_DETAILS_URL = BASE_URL + "bulkDetails";
	public static final String REPLICATE_LIBRARY_URL = BASE_URL + "replicateLibrary";
	public static final String UPLOAD_DEVICE_CONFIG_IRL = BASE_URL + "uploadDeviceConfig";
	public static final String TOC_URL = BASE_URL + "toc";
	protected static final String REQUEST_CONTENT_TYPE = "application/x-protobuf";
	private static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
	private static final SimpleDateFormat PATTERN_RFC1123_FORMAT = new SimpleDateFormat(PATTERN_RFC1123);

	protected static void prepareConnection(HttpURLConnection connection, boolean post, RequestInfo info) {
		if (post) {
			Client.prepareConnection(connection, false);
			connection.setRequestProperty(REQUEST_CONTENT_TYPE_FIELD, REQUEST_CONTENT_TYPE);
		} else {
			connection.setUseCaches(false);
			connection.setDoInput(true);
		}
		connection.setRequestProperty("X-DFE-MCCMNC", info.get(RequestInfo.KEY_CELL_OPERATOR_NUMERIC));
		connection.setRequestProperty("Authorization",
									  "GoogleLogin auth=" + info.get(RequestInfo.KEY_AUTHORIZATION_TOKEN));
		connection.setRequestProperty("X-DFE-Device-Id", info.get(RequestInfo.KEY_ANDROID_ID_HEX));
		connection.setRequestProperty("X-DFE-Client-Id", info.get(RequestInfo.KEY_CLIENT_ID));
		connection.setRequestProperty("X-DFE-Logging-Id", info.get(RequestInfo.KEY_LOGGING_ID));
		connection.setRequestProperty("X-DFE-SmallestScreenWidthDp", info.get(RequestInfo.KEY_SMALEST_SCREEN_WIDTH_DP));
		connection.setRequestProperty("X-DFE-Filter-Level", info.get(RequestInfo.KEY_FILTER_LEVEL));
		connection.setRequestProperty("Accept-Language", "en-GB");
		setUserAgent(connection, info);
	}

	public static Unsorted.TocResponse requestToc(String shh, String deviceConfigToken, RequestInfo info) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(TOC_URL + "?shh=" + shh).openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		prepareConnection(connection, false, info);
		if (deviceConfigToken != null) {
			connection.setRequestProperty("X-DFE-Device-Config-Token", deviceConfigToken);
			try {
				Date date = new Date(Long.parseLong(deviceConfigToken));
				connection.setRequestProperty("If-Modified-Since", PATTERN_RFC1123_FORMAT.format(date));
				connection.setRequestProperty("If-None-Match", "3690"); // TODO: What's this?!
			} catch (Exception e) {
				// Ignore
			}
		}
		beforeRequest(connection);
		byte[] bytes = readData(connection, false);
		try {
			return Requests.ResponseWrapper.parseFrom(bytes).getPayload().getTocResponse();
		} catch (InvalidProtocolBufferMicroException e) {
			throw new RuntimeException(e);
		}
	}

	public static Browse.BrowseResponse requestBrowse(String url, RequestInfo info) {
		if (url.startsWith(BASE_URL))
			url = url.substring(BASE_URL.length());
		if (url.startsWith("browse"))
			url = url.substring(6);
		if (url.startsWith("?"))
			url = url.substring(1);
		return simpleGetRequest(BROWSE_URL + "?" + url, info).getBrowseResponse();
	}

	public static Documents.BulkDetailsResponse sendBulkDetailsRequest(Documents.BulkDetailsRequest request,
																	   RequestInfo info) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(BULK_DETAILS_URL).openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		prepareConnection(connection, true, info);
		writeData(connection, request.toByteArray(), false);
		byte[] bytes = readData(connection, false);
		try {
			return Requests.ResponseWrapper.parseFrom(bytes).getPayload().getBulkDetailsResponse();
		} catch (InvalidProtocolBufferMicroException e) {
			throw new RuntimeException(e);
		}
	}

	public static Library.LibraryReplicationResponse sendLibraryReplicationRequest(
			Library.LibraryReplicationRequest request, RequestInfo info) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(REPLICATE_LIBRARY_URL).openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		prepareConnection(connection, true, info);
		writeData(connection, request.toByteArray(), false);
		byte[] bytes = readData(connection, false);
		try {
			return Requests.ResponseWrapper.parseFrom(bytes).getPayload().getLibraryReplicationResponse();
		} catch (InvalidProtocolBufferMicroException e) {
			throw new RuntimeException(e);
		}
	}

	public static Documents.ListResponse requestSuggest(String url, RequestInfo info) {
		if (url.startsWith(BASE_URL))
			url = url.substring(BASE_URL.length());
		if (url.startsWith("suggest"))
			url = url.substring(7);
		if (url.startsWith("?"))
			url = url.substring(1);
		return simpleGetRequest(SUGGEST_URL + "?" + url, info).getListResponse();
	}

	private static Requests.Payload simpleGetRequest(String url, RequestInfo info) {
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(url).openConnection();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		prepareConnection(connection, false, info);
		beforeRequest(connection);
		byte[] bytes = readData(connection, false);
		try {
			return Requests.ResponseWrapper.parseFrom(bytes).getPayload();
		} catch (InvalidProtocolBufferMicroException e) {
			throw new RuntimeException(e);
		}
	}

	public static Documents.ListResponse requestList(String url, RequestInfo info) {
		if (url.startsWith(BASE_URL))
			url = url.substring(BASE_URL.length());
		if (url.startsWith("list"))
			url = url.substring(4);
		if (url.startsWith("?"))
			url = url.substring(1);
		return simpleGetRequest(LIST_URL + "?" + url, info).getListResponse();
	}
}