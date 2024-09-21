
const prod = {
    url: {
        BASE_URL: 'https://www',
    }
}

const dev = {
    url: {
        BASE_URL: 'http://localhost:8080/api'
    }
}


export const config = process.env.NODE_ENV === 'development' ? dev : prod