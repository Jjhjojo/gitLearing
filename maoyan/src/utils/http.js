import axios from 'axios'

class Http{
    constructor(){
        this.instance = axios.create({
            baseURL: 'http://localhost:8081',
            timeout: 60000,
          })
    }
    get({url,params={}}){
        return new Promise((resolve,reject)=> {
            this.instance.get(url,{
                params
            })
            .then((result)=>{
                resolve(result.data)
            })
            .catch((err) => {
                reject(err)
            })
        }) 
    }
}
export default Http