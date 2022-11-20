import "./Field.scss";
import React from "react";
import Box from "@mui/material/Box";
import { Typography } from "@mui/material";

export function Field({ icon, fieldText, status }) {
  return (
    <Box className={`field-container ${status}`}>
      <Typography className="field-icon">{icon}</Typography>
      <Typography className="field-description">{fieldText}</Typography>
    </Box>
  );
}
