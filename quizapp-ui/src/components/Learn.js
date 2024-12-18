import React, { useState, useEffect } from "react";
import {
  Container,
  Header,
  Segment,
  Button,
  Grid,
  Dropdown,
} from "semantic-ui-react";
import axios from "axios";
import { config } from "../Constants";

const instance = axios.create({
  baseURL: config.url.API_BASE_URL,
});

const Learn = () => {
  const [questions, setQuestions] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("Tất cả");
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        let url = "/question/allQuestions";
        if (selectedCategory !== "Tất cả") {
          url = `/question/category/${selectedCategory}`;
        }
        const response = await instance.get(url);
        setQuestions(response.data);
      } catch (error) {
        console.error("Error fetching questions:", error);
      }
    };

    fetchQuestions();
  }, [selectedCategory]);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        let url = "/question/allCategories";
        const response = await instance.get(url);
        setCategories(["Tất cả", ...response.data]);
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    };

    fetchCategories();
  });

  const handleCategoryChange = (e, { value }) => {
    setSelectedCategory(value);
  };

  const handleDelete = async (id) => {
    try {
      // Gửi yêu cầu DELETE tới backend
      const response = await instance.delete(`/question/delete/${id}`);
      if (response.status === 200) {
        // Cập nhật lại danh sách câu hỏi sau khi xóa
        setQuestions(questions.filter((question) => question.id !== id));
      }
    } catch (error) {
      console.error("Error deleting question:", error);
    }
  };

  const categoryOptions = categories.map((category) => ({
    key: category.toLowerCase(),
    value: category,
    text: category,
  }));

  return (
    <Container className="learn">
      <Grid columns={2} verticalAlign="middle">
        <Grid.Column width={8}>
          <Header as="h1">Học tập</Header>
        </Grid.Column>
        <Grid.Column width={8} textAlign="right">
          <Dropdown
            placeholder="Chọn nhóm câu hỏi"
            selection
            options={categoryOptions}
            onChange={handleCategoryChange}
            value={selectedCategory}
          />
        </Grid.Column>
      </Grid>
      <Grid>
        <Grid.Row>
          <Grid.Column width={16}>
            <Segment>
              {questions.map((question) => (
                <Segment key={question.id}>
                  <div
                    style={{
                      display: "flex",
                      justifyContent: "space-between",
                      alignItems: "center",
                    }}
                  >
                    <div>
                      <Header as="h3">{question.questionTitle}</Header>
                      <p>
                        <strong>Đáp án:</strong>{" "}
                        <span style={{ color: "green" }}>
                          {question.rightAnswer}
                        </span>
                      </p>
                    </div>
                    <Button
                      color="red"
                      onClick={() => handleDelete(question.id)}
                    >
                      Xóa
                    </Button>
                  </div>
                </Segment>
              ))}
            </Segment>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    </Container>
  );
};

export default Learn;
