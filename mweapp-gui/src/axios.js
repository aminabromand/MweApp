import axios from 'axios'

// axios.defaults.baseURL = "http://localhost:8080"
const myBaseUrl = process.env.VUE_APP_LOCAL_BASE_URL ? 'http://localhost:8080' : 'https://afternoon-mesa-12438.herokuapp.com'
// axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem("token");
// axios.defaults.headers.common["Content-Type"] = "application/json";

export const $axios = axios.create({
  // baseURL: 'https://afternoon-mesa-12438.herokuapp.com'
  baseURL: myBaseUrl
})
