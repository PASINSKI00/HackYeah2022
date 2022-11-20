import "./Tasks.scss";
import React from "react";
import Box from "@mui/material/Box";
import Header from "../../components/Header";
import { Task } from "../../components/Task/Task";

export function Tasks() {
  const description = "Zapotrzebowanie ludzkości na zasoby wody wzrosło ponad dwukrotnie od lat 60 XX...";
  const descriptionTwo = "Transport drogowy odpowiada za około jedną piątą emisji w UE. Emisje CO2...";
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
        <Task
          title="Dzień szybkiego prysznica"
          description={description}
          points="1"
          savings="-100 litrów"
          status="completed"
          src="/images/undraw1.svg"
        />
        <Task
          title="Dzień transportu publicznego"
          description={descriptionTwo}
          points="15"
          savings="-160 g/km"
          status="pending"
          src="/images/undraw2.svg"
        />
      </Box>
    </Box>
  );
}
