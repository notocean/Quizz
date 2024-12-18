import React, { useState, useEffect } from "react";
import {
  Container,
  Header,
  Segment,
  Form,
  Button,
  Message,
  Table,
} from "semantic-ui-react";
import axios from "axios";
import { config } from "../Constants";
import AttemptQuiz from "./AttemptQuiz";

const instance = axios.create({
  baseURL: config.url.API_BASE_URL,
});

const Attempt = () => {
  const [category, setCategory] = useState("");
  const [numQ, setNumQ] = useState("");
  const [title, setTitle] = useState("");
  const [responseMessage, setResponseMessage] = useState("");
  const [quizes, setQuizzes] = useState([]);
  const [openQuizModal, setOpenQuizModal] = useState(false);
  const [selectedQuizId, setSelectedQuizId] = useState(null);

  useEffect(() => {
    fetchAllQuizzes();
  }, []);

  const fetchAllQuizzes = async () => {
    try {
      const response = await instance.get("/quiz/allquizes");
      setQuizzes(response.data);
    } catch (error) {
      console.error("Failed to fetch quizzes", error);
    }
  };

  const createQuiz = async () => {
    try {
      const response = await instance.post(
        `/quiz/create?category=${category}&numQ=${numQ}&title=${title}`
      );
      if (response.status === 201) {
        setResponseMessage("Tạo bài kiểm tra thành công");
        fetchAllQuizzes();
        setCategory("");
        setNumQ("");
        setTitle("");
      }
    } catch (error) {
      setResponseMessage("Failed to create quiz");
    }
  };

  const handleAttemptClick = (quizId) => {
    setSelectedQuizId(quizId);
    setOpenQuizModal(true);
  };

  const deleteQuiz = async (quizId) => {
    try {
      const response = await instance.delete(`/quiz/delete/${quizId}`);
      if (response.status === 200) {
        setResponseMessage("Xóa bài kiểm tra thành công");
        fetchAllQuizzes();
      }
    } catch (error) {
      setResponseMessage("Failed to delete quiz");
    }
  };

  return (
    <Container className="attempt-quiz">
      <Header as="h1">Tạo bài kiểm tra</Header>
      <Segment>
        {responseMessage && (
          <Message
            positive={responseMessage === "Tạo bài kiểm tra thành công"}
            negative={responseMessage === "Tạo bài kiểm tra thất bại"}
          >
            <Message.Header>{responseMessage}</Message.Header>
          </Message>
        )}
        <Form>
          <Form.Input
            label="Nhóm câu hỏi"
            placeholder="Nhập nhóm câu hỏi"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          />
          <Form.Input
            label="Số lượng câu hỏi"
            placeholder="Nhập số lượng câu hỏi sẽ có trong bài kiểm tra"
            value={numQ}
            onChange={(e) => setNumQ(e.target.value)}
          />
          <Form.Input
            label="Tiêu đề bài kiểm tra"
            placeholder="Nhập tiêu đề bài kiểm tra"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <Button primary onClick={createQuiz}>
            Tạo bài kiểm tra
          </Button>
        </Form>
      </Segment>
      <Segment>
        <Header as="h1">Tất cả các bài kiểm tra</Header>
        <Table celled>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Tiêu đề</Table.HeaderCell>
              <Table.HeaderCell>Hành động</Table.HeaderCell>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {quizes.map((quiz) => (
              <Table.Row key={quiz.id}>
                <Table.Cell>{quiz.title}</Table.Cell>
                <Table.Cell>
                  <AttemptQuiz
                    quizId={quiz.id}
                    quizTitle={quiz.title}
                    open={openQuizModal && selectedQuizId === quiz.id}
                    setOpen={setOpenQuizModal}
                    handleAttemptClick={handleAttemptClick}
                  />

                  <Button color="red" onClick={() => deleteQuiz(quiz.id)}>
                    Xóa
                  </Button>
                </Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </Segment>
    </Container>
  );
};

export default Attempt;
