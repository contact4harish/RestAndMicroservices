package com.Spring.RestAndMicroServices.Entity;

public class Posts {
	
	private Integer postId;
	
	private String postMessage;

	public long getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}

	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", postMessage=" + postMessage + ", getPostId()=" + getPostId()
				+ ", getPostMessage()=" + getPostMessage() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public Posts(Integer postId, String postMessage) {
		super();
		this.setPostId(postId);
		this.setPostMessage(postMessage);
		// TODO Auto-generated constructor stub
	}

}
