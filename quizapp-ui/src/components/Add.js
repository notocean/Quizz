import React, { useState } from "react";
import {
  Container,
  Header,
  Segment,
  Grid,
  Form,
  Button,
  Message,
} from "semantic-ui-react";
import axios from "axios";
import { config } from "../Constants";

const instance = axios.create({
  baseURL: config.url.API_BASE_URL,
});

const Add = () => {
  const [questionData, setQuestionData] = useState({
    questionTitle: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    rightAnswer: "",
    category: "",
  });
  const [responseMessage, setResponseMessage] = useState("");

  const handleInputChange = (e, { name, value }) => {
    setQuestionData({ ...questionData, [name]: value });
  };

  const handleSubmit = async () => {
    try {
      const response = await instance.post("/question/add", questionData);
      if (response.status === 201) {
        setResponseMessage("Thêm câu hỏi thành công");
        setQuestionData({
          questionTitle: "",
          option1: "",
          option2: "",
          option3: "",
          option4: "",
          rightAnswer: "",
          category: "",
        });
      }
    } catch (error) {
      setResponseMessage("Error adding question");
    }
  };

  return (
    <Container className="learn">
      <Grid columns={2} verticalAlign="middle">
        <Grid.Column width={8}>
          <Header as="h1">Thêm câu hỏi</Header>
        </Grid.Column>
      </Grid>
      <Segment>
        {responseMessage && (
          <Message
            positive={responseMessage === "Thêm câu hỏi thành công"}
            negative={responseMessage === "Thêm câu hỏi thất bại"}
          >
            <Message.Header>{responseMessage}</Message.Header>
          </Message>
        )}
        <Form onSubmit={handleSubmit}>
          <Form.Input
            label="Nội dung câu hỏi"
            placeholder="Nhập câu hỏi"
            value={questionData.questionTitle}
            name="questionTitle"
            onChange={handleInputChange}
          />
          {[1, 2, 3, 4].map((num) => (
            <Form.Input
              key={num}
              label={`Đáp án ${num}`}
              placeholder={`Nhập đáp án ${num}`}
              value={questionData[`option${num}`]}
              name={`option${num}`}
              onChange={handleInputChange}
            />
          ))}
          <Form.Input
            label="Đáp án đúng"
            placeholder="Nhập đáp án đúng"
            value={questionData.rightAnswer}
            name="rightAnswer"
            onChange={handleInputChange}
          />
          <Form.Input
            label="Nhóm câu hỏi"
            placeholder="Nhập nhóm câu hỏi"
            title="Các bài kiểm tra sẽ lựa chọn các câu hỏi có cùng nhóm"
            value={questionData.category}
            name="category"
            onChange={handleInputChange}
          />
          <Button primary type="submit">
            Thêm
          </Button>
        </Form>
      </Segment>
    </Container>
  );
};

export default Add;
