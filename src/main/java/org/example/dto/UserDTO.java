package org.example.dto;

import org.example.entity.User;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) для сущности "Пользователь".
 */
public class UserDTO {
    private Long id;
    private String username;
    private String email;

    /**
     * Пустой конструктор класса UserDTO.
     */
    public UserDTO() {
    }

    /**
     * Конструктор класса UserDTO с параметрами.
     *
     * @param id       идентификатор пользователя
     * @param username имя пользователя
     * @param email    адрес электронной почты пользователя
     */
    public UserDTO(Long id, String username, String email) {
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

    /**
     * Переопределение метода equals для сравнения объектов UserDTO.
     *
     * @param obj объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserDTO user = (UserDTO) obj;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }

    /**
     * Переопределение метода hashCode для вычисления хеш-кода объекта UserDTO.
     *
     * @return хеш-код объекта UserDTO
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, email);
    }

    /**
     * Метод для преобразования объекта типа User в объект типа UserDTO.
     *
     * @param user объект типа User
     * @return объект типа UserDTO
     */
    public static UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
