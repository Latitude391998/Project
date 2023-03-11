package project.response;

import project.entity.Status;

public class RequestDto {

	private Long requestId;

	private Status status;

	public RequestDto() {
		super();
	}

	public RequestDto(Long requestId, Status status) {
		super();
		this.requestId = requestId;
		this.status = status;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequestDto [requestId=" + requestId + ", status=" + status + "]";
	}

}
