package ru.job4j.tracker.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());

    private Connection cn;

    public SqlTracker() {

    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    /**
     * Метод записывает поля заявки в базу данных.
     *
     * @param item - новая заявка.
     * @return - заявка, если успешно добавлена.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement(
                "insert into items(name, priority) values(?, ?)",
                Statement.RETURN_GENERATED_KEYS)
        ) {
            st.setString(1, item.getName());
            st.setInt(2, item.getPriority());
            st.executeUpdate();
            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getString(1));
                    return item;
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Метод обновляет поля заявки, соответствующей id.
     *
     * @param id - уникальное поле заявки.
     * @param item - обновленная заявка.
     * @return - успех выполнения.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement st = cn.prepareStatement("update items set (name, priority) = (?, ?) where id = ?")) {
            st.setString(1, item.getName());
            st.setInt(2, item.getPriority());
            st.setInt(3, Integer.parseInt(id));
            st.executeUpdate();
            result = st.executeUpdate() > 0;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод удаляет из базы запись соответствующую id.
     *
     * @param id -уникальное поле заявки.
     * @return - успех выполнения.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement st = cn.prepareStatement("delete from items where id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            st.executeUpdate();
            result = st.executeUpdate() > 0;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод копирует в лист все записи базы.
     *
     * @return - лист со всеми записями базы.
     */
    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("select * from items")) {
            while (rs.next()) {
                result.add(
                        new Item(
                                rs.getString("name"),
                                rs.getInt("priority"),
                                String.valueOf(rs.getInt("id"))
                        ));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод копирует в лист все записи, с именем key.
     *
     * @param key - ключ-имя.
     * @return лист с заявками с именами key.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement(
                "select * from items as i where i.name = ?"
        )) {
            st.setString(1, key);
            try (ResultSet rs = st.executeQuery()) {
               while (rs.next()) {
                   result.add(
                           new Item(
                                   rs.getString("name"),
                                   rs.getInt("priority"),
                                   String.valueOf(rs.getInt("id"))
                           ));
               }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Метод вернет из базы заявку с соответствующим id.
     *
     * @param id - уникальное поле заявки.
     * @return - заявка.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement st = cn.prepareStatement("select * from items as i where i.id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    result = new Item(
                            rs.getString("name"),
                            rs.getInt("priority"),
                            String.valueOf(rs.getInt("id"))
                    );
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}