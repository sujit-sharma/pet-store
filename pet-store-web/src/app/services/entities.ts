export interface UserEntity {
  id?: number;
  username: string;
  firstName: string;
  lastName: string;
  phone: string;
  password: string;
  authorities: Array<string>;
}
