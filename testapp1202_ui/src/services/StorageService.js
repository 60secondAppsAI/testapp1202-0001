import http from "../http-common"; 

class StorageService {
  getAllStorages(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/storage/storages`, searchDTO);
  }

  get(storageId) {
    return this.getRequest(`/storage/${storageId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/storage?field=${matchData}`, null);
  }

  addStorage(data) {
    return http.post("/storage/addStorage", data);
  }

  update(data) {
  	return http.post("/storage/updateStorage", data);
  }
  
  uploadImage(data,storageId) {
  	return http.postForm("/storage/uploadImage/"+storageId, data);
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

export default new StorageService();
