import "./Tasks.scss";
import React from "react";
import Box from "@mui/material/Box";
import Header from "../../components/Header";

export function Tasks() {
  return (
    <Box className="tasks-container">
      <Header selected="tasks" />
      <Box className="tasks-content-containers">
        <Box className="greeting-container">Witaj, Maria! 👋</Box>
        <Box className="daily-stats-container">
          <Box className="completed-stat">
            Ukończone <Box>1</Box>
          </Box>
          <Box className="pending-stat">
            Do rozpoczęcia <Box>1</Box>
          </Box>
        </Box>
        <Box className="greeting-container daily">Twoje dzienne zadania</Box>
      </Box>
    </Box>
  );
}
