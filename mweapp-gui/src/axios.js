import axios from 'axios'

// axios.defaults.baseURL = "http://localhost:8080"
// axios.defaults.baseURL = import.meta.env.DEV ? "http://localhost:8080": "example.com";
// axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem("token");
// axios.defaults.headers.common["Content-Type"] = "application/json";

export const $axios = axios.create({
  // baseURL: 'http://localhost:8080'
  baseURL: 'https://afternoon-mesa-12438.herokuapp.com'
})
