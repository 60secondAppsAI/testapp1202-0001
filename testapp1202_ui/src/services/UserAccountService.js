import http from "../http-common"; 

class UserAccountService {
  getAllUserAccounts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/userAccount/userAccounts`, searchDTO);
  }

  get(userAccountId) {
    return this.getRequest(`/userAccount/${userAccountId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/userAccount?field=${matchData}`, null);
  }

  addUserAccount(data) {
    return http.post("/userAccount/addUserAccount", data);
  }

  update(data) {
  	return http.post("/userAccount/updateUserAccount", data);
  }
  
  uploadImage(data,userAccountId) {
  	return http.postForm("/userAccount/uploadImage/"+userAccountId, data);
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

export default new UserAccountService();
