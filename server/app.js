const express = require("express");

const path = require("path");
const morgan = require("morgan");
const cookieParser = require("cookie-parser");
const session = require("express-session");
const dotenv = require("dotenv");

dotenv.config();
const webSocket = require("./socket");

const connect = require("./schemas");

const app = express();
const http = require("http").Server(app);
const cors = require("cors");
app.set("port", process.env.PORT || 5000);
const corsOpt = {
  origin: "http://localhost:3000",
  credentials: true,
};
app.use(cors(corsOpt));

connect();

const sessionMiddleware = session({
  resave: false,
  saveUninitialized: false,
  secret: process.env.COOKIE_SECRET,
  cookie: {
    httpOnly: true,
    secure: false,
  },
});
app.use(morgan("dev"));
app.use(express.static(path.join(__dirname, "public")));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser(process.env.COOKIE_SECRET));
app.use(sessionMiddleware);
const indexRouter = require("./routes");
app.use("/", indexRouter);

app.use((req, res, next) => {
  const error = new Error(`${req.method} ${req.url} 라우터가 없습니다.`);
  error.status = 404;
  next(error);
});

app.use((err, req, res, next) => {
  res.locals.message = err.message;
  res.locals.error = process.env.NODE_ENV !== "production" ? err : {};
  res.status(err.status || 500);
});

const server = app.listen(app.get("port"), () => {
  console.log(app.get("port"), "번 포트 연결 체크 정상");
});

webSocket(server, app, sessionMiddleware);
