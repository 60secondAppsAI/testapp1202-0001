import http from "../http-common"; 

class PreliminaryDiscussionService {
  getAllPreliminaryDiscussions(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/preliminaryDiscussion/preliminaryDiscussions`, searchDTO);
  }

  get(preliminaryDiscussionId) {
    return this.getRequest(`/preliminaryDiscussion/${preliminaryDiscussionId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/preliminaryDiscussion?field=${matchData}`, null);
  }

  addPreliminaryDiscussion(data) {
    return http.post("/preliminaryDiscussion/addPreliminaryDiscussion", data);
  }

  update(data) {
  	return http.post("/preliminaryDiscussion/updatePreliminaryDiscussion", data);
  }
  
  uploadImage(data,preliminaryDiscussionId) {
  	return http.postForm("/preliminaryDiscussion/uploadImage/"+preliminaryDiscussionId, data);
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

export default new PreliminaryDiscussionService();
