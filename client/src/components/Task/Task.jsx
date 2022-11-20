import "./Task.scss";
import React from "react";
import Box from "@mui/material/Box";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import ScoreboardIcon from "@mui/icons-material/Scoreboard";
import InvertColorsIcon from "@mui/icons-material/InvertColors";
import { Typography } from "@mui/material";
import Field from "../Field";

export function Task({ src, title, description, points, savings, status }) {
  return (
    <Box className={`task-container ${status}`}>
      <Box className="task-top-container ">
        <Field icon={<CheckCircleOutlineIcon />} fieldText="Ukończone" status={status} />
        <img src={src} alt="pic" />
      </Box>
      <Box className="task-text-container">
        <Typography className="task-text-title">{title}</Typography>
        <Typography className="task-text-description">{description}</Typography>
      </Box>
      <Box className="task-stats-container">
        <Field icon={<ScoreboardIcon />} fieldText={points} status="none" />
        <Field icon={<InvertColorsIcon />} fieldText={savings} status="none" />
      </Box>
    </Box>
  );
}
