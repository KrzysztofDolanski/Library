package com.epam.library.readers;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReaderDAOTest {

//
//    @Mock
//    JdbcTemplate jdbcTemplate;
//
//
//    public Connection getConnection() throws SQLException {
//        Connection conn = null;
//        Properties connectionProps = new Properties();
//        connectionProps.put("user", "sa");
//        connectionProps.put("password", "");
//
//        conn = DriverManager.getConnection(
//                "jdbc:h2:mem:testdb",
//                connectionProps);
//        System.out.println("Connected to database");
//        return conn;
//    }
//
//    @Test
//    void shouldFindReaderById(){
//        //given
//        ReaderDAO readerDAO;
//        try {
//            readerDAO = new ReaderDAO(getConnection());
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//        readerDAO.create(makeReader());
//        readerDAO.create(makeReader());
//        readerDAO.create(makeReader());
//        readerDAO.create(makeReader());
//        readerDAO.create(makeReader());
//        Reader byId = readerDAO.findById(5);
//        //when
//        long id = byId.getId();
//        //then
//        assertEquals(5,id);
//    }
//
//    @Test
//    void shouldSaveReaderInDatabase(){
//        //given
//        ReaderDAO readerDAO;
//        try {
//            readerDAO = new ReaderDAO(getConnection());
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//        readerDAO.create(makeReader());
//        Reader lastReader = readerDAO.findLastReader();
//        Reader reader = makeReader();
//        long lastId = lastReader.getId();
//        long lastIdAfterIncrement = ++lastId;
//
//        //when
//        assertNull(readerDAO.findById(lastIdAfterIncrement).getName());
//        readerDAO.create(reader);
//
//        //then
//        assertNotNull(readerDAO.findById(lastIdAfterIncrement));
//    }
//
//    @Test
//    void shouldDeleteReaderById(){
//        //given
//        ReaderDAO readerDAO;
//        try {
//            readerDAO = new ReaderDAO(getConnection());
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//        readerDAO.create(makeReader());
//        Reader lastReader = readerDAO.findLastReader();
//        //when
//        Reader readerById = readerDAO.findById(lastReader.getId());
//        assertNotNull(readerById);
//        assertEquals(lastReader, readerById);
//
//        readerDAO.deleteById(lastReader.getId());
//        Reader deleted = readerDAO.findById(lastReader.getId());
//        //then
//
//        assertNotEquals(lastReader, deleted);
//    }
//
//    private Reader makeReader() {
//        Reader reader = new Reader();
//        reader.setName("Jan");
//        reader.setSurname("Kowalski");
//        reader.setEmail("kowalski@kowalski.eu");
//        return reader;
//    }
//
//    @AfterEach
//    void clearTable(){
//        //given
//        ReaderDAO readerDAO;
//        try {
//            readerDAO = new ReaderDAO(getConnection());
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//        readerDAO.deleteAllReaders();
//    }
}
