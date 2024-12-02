import http from "../http-common"; 

class DocumentStoreService {
  getAllDocumentStores(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/documentStore/documentStores`, searchDTO);
  }

  get(documentStoreId) {
    return this.getRequest(`/documentStore/${documentStoreId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/documentStore?field=${matchData}`, null);
  }

  addDocumentStore(data) {
    return http.post("/documentStore/addDocumentStore", data);
  }

  update(data) {
  	return http.post("/documentStore/updateDocumentStore", data);
  }
  
  uploadImage(data,documentStoreId) {
  	return http.postForm("/documentStore/uploadImage/"+documentStoreId, data);
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

export default new DocumentStoreService();
