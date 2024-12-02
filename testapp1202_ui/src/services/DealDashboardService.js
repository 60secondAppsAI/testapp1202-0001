import http from "../http-common"; 

class DealDashboardService {
  getAllDealDashboards(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/dealDashboard/dealDashboards`, searchDTO);
  }

  get(dealDashboardId) {
    return this.getRequest(`/dealDashboard/${dealDashboardId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/dealDashboard?field=${matchData}`, null);
  }

  addDealDashboard(data) {
    return http.post("/dealDashboard/addDealDashboard", data);
  }

  update(data) {
  	return http.post("/dealDashboard/updateDealDashboard", data);
  }
  
  uploadImage(data,dealDashboardId) {
  	return http.postForm("/dealDashboard/uploadImage/"+dealDashboardId, data);
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

export default new DealDashboardService();
