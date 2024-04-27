package org.example.entity;

/**
 * Класс, представляющий пользователя.
 */
public class User {
    private Long id; // Идентификатор пользователя
    private String username; // Имя пользователя
    private String email; // Адрес электронной почты пользователя

    /**
     * Пустой конструктор класса User.
     */
    public User() {
    }

    /**
     * Конструктор класса User с параметрами.
     *
     * @param id       идентификатор пользователя
     * @param username имя пользователя
     * @param email    адрес электронной почты пользователя
     */
    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    /**
     * Получить идентификатор пользователя.
     *
     * @return идентификатор пользователя
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить идентификатор пользователя.
     *
     * @param id идентификатор пользователя
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить имя пользователя.
     *
     * @return имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Установить имя пользователя.
     *
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Получить адрес электронной почты пользователя.
     *
     * @return адрес электронной почты пользователя
     */
    public String getEmail() {
        return email;
    }

    /**
     * Установить адрес электронной почты пользователя.
     *
     * @param email адрес электронной почты пользователя
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
