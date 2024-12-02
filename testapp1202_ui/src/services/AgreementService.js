import http from "../http-common"; 

class AgreementService {
  getAllAgreements(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/agreement/agreements`, searchDTO);
  }

  get(agreementId) {
    return this.getRequest(`/agreement/${agreementId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/agreement?field=${matchData}`, null);
  }

  addAgreement(data) {
    return http.post("/agreement/addAgreement", data);
  }

  update(data) {
  	return http.post("/agreement/updateAgreement", data);
  }
  
  uploadImage(data,agreementId) {
  	return http.postForm("/agreement/uploadImage/"+agreementId, data);
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

export default new AgreementService();
