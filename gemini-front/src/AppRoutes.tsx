import React from "react";
import { Route, Switch, useLocation } from "react-router-dom";
import Header from "./components/common/header/Header";
import Main from "./pages/main/Main";
import Login from "./pages/auth/Login";
import LoginSuccess from "./pages/auth/LoginSuccess";
import MyPage from "./pages/profile/MyPage";

const AppRoutes: React.FC = () => {
  const location = useLocation();
  const pathsWithoutHeader = ["/login", "/loginSuccess"];
  const shouldShowHeader = !pathsWithoutHeader.includes(location.pathname);

  return (
    <>
      {shouldShowHeader && <Header />}
      <Switch>
        <Route exact path="/" component={Main} />
        <Route exact path="/loginPage" component={Login} />
        <Route exact path="/loginSuccess" component={LoginSuccess} />
        {/* ... */}
      </Switch>
    </>
  );
};

export default AppRoutes;
