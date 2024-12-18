// src/App.js
import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import { Container, Header, Button, Grid } from "semantic-ui-react";
import Learn from "./components/Learn";
import Attempt from "./components/Attempt";
import Add from "./components/Add";

const App = () => {
  return (
    <Router>
      <Container className="container">
        <Grid className="grid">
          <Grid.Row>
            <Grid.Column>
              <Header
                as="h1"
                className="header1"
                style={{ marginBottom: "2rem", fontSize: "2.5rem" }}
              >
                HỌC VÀ KIỂM TRA
              </Header>

              <Button as={Link} to="/Learn" primary>
                Học tập
              </Button>
              <Button as={Link} to="/Attempt" secondary>
                Kiểm tra
              </Button>
              <Button as={Link} to="/Add" secondary>
                Thêm câu hỏi
              </Button>
            </Grid.Column>
          </Grid.Row>

          <Routes>
            <Route path="/Learn" element={<Learn />} />
            <Route path="/Attempt" element={<Attempt />} />
            <Route path="/Add" element={<Add />} />
            <Route path="/" element={<Learn />} />
          </Routes>
        </Grid>
      </Container>
    </Router>
  );
};

export default App;
