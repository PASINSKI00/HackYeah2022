import "./Field.scss";
import React from "react";
import Box from "@mui/material/Box";
import { Typography } from "@mui/material";

export function Field({ icon, fieldText }) {
  return (
    <Box className="field-container">
      <Typography className="field-icon">{icon}</Typography>
      <Typography className="field-description">{fieldText}</Typography>
    </Box>
  );
}
