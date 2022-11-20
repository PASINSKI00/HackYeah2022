import "./Task.scss";
import React from "react";
import Box from "@mui/material/Box";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import Field from "../Field";

export function Task() {
  return (
    <Box className="task-container">
      <Field icon={<CheckCircleOutlineIcon />} fieldText="Ukończone" />
    </Box>
  );
}
