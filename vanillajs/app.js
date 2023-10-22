const express = require("express");
const fs = require("fs");
const path = require("path");
const dotenv = require('dotenv');
const fileUpload = require("express-fileupload");
const exp = require("constants");
const ffmpeg = require
const app = express();


dotenv.config();
const router = express.Router();

app.use(express.json());
app.use(express.urlencoded({extended : true}))
app.use(express.static('../public'))
app.use(fileUpload())

app.set('port', process.env.PORT || 8001);
app.set('views','../views')


app.get('/', (req, res) => {
    res.render('index')
})

// port 생성 서버 실행
app.listen(app.get('port'), () => {
    console.log(`http://localhost:${app.get('port')} 번 포트에서 대기중`);
  });
