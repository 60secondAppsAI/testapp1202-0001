import http from "../http-common"; 

class EmailNotificationService {
  getAllEmailNotifications(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/emailNotification/emailNotifications`, searchDTO);
  }

  get(emailNotificationId) {
    return this.getRequest(`/emailNotification/${emailNotificationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/emailNotification?field=${matchData}`, null);
  }

  addEmailNotification(data) {
    return http.post("/emailNotification/addEmailNotification", data);
  }

  update(data) {
  	return http.post("/emailNotification/updateEmailNotification", data);
  }
  
  uploadImage(data,emailNotificationId) {
  	return http.postForm("/emailNotification/uploadImage/"+emailNotificationId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new EmailNotificationService();
