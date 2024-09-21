import api from "../api/axiosConfig";


class CompanyService {
    //we passed a company object to create company
    saveCompany(company) {

        //creating a post request
        console.log(company)
        return api.post(`/company`, company);
        
    }


    //return a list of company
    getCompanies() {
        return api.get(`/companies`);
        
    }

    //get company by Id
    getCompyById(cmp_id) {
        return api.get(`/company/${cmp_id}`);
        
    }

    //delete company by Id
    deleteCompany(cmp_id) {
        return api.delete(`/company/${cmp_id}`)
        
    }

    //update company
    updateCompany(company, cmp_id) {
        return api.put(`/company/${cmp_id}`, company);
        
    }
}

export default new CompanyService();