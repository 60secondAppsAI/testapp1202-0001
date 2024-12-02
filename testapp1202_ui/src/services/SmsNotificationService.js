import http from "../http-common"; 

class SmsNotificationService {
  getAllSmsNotifications(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/smsNotification/smsNotifications`, searchDTO);
  }

  get(smsNotificationId) {
    return this.getRequest(`/smsNotification/${smsNotificationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/smsNotification?field=${matchData}`, null);
  }

  addSmsNotification(data) {
    return http.post("/smsNotification/addSmsNotification", data);
  }

  update(data) {
  	return http.post("/smsNotification/updateSmsNotification", data);
  }
  
  uploadImage(data,smsNotificationId) {
  	return http.postForm("/smsNotification/uploadImage/"+smsNotificationId, data);
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

export default new SmsNotificationService();
