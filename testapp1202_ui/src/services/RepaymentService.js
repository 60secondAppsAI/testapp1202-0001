import http from "../http-common"; 

class RepaymentService {
  getAllRepayments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/repayment/repayments`, searchDTO);
  }

  get(repaymentId) {
    return this.getRequest(`/repayment/${repaymentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/repayment?field=${matchData}`, null);
  }

  addRepayment(data) {
    return http.post("/repayment/addRepayment", data);
  }

  update(data) {
  	return http.post("/repayment/updateRepayment", data);
  }
  
  uploadImage(data,repaymentId) {
  	return http.postForm("/repayment/uploadImage/"+repaymentId, data);
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

export default new RepaymentService();
