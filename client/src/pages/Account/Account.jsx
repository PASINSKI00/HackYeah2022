import "./Account.scss";
import React from "react";
import Box from "@mui/material/Box";
import { Button, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import Header from "../../components/Header";
import AuthService from "../../services/authService";

export function Account() {
  const navigate = useNavigate();

  const onLogout = () => {
    AuthService.logout();
    return navigate("/");
  };

  return (
    <Box className="account-container">
      <Header selected="account" />
      <Box className="account-content-container">
        <Typography>Your account</Typography>
        <Button onClick={() => onLogout()}>Logout</Button>
      </Box>
    </Box>
  );
}
