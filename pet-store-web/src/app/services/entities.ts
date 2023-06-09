
export interface UserEntity {
  id?: number;
  username: string;
  firstName: string;
  lastName: string;
  phone: string;
  email: string;
  password: string;
  authorities: Array<string>;
}

export interface PetEntity {
  id?: number,
  name: string,
  category: CategoryEntity,
  photoUrls: string[],
  tags: TagEntity[],
  status: string
}
export interface TagEntity {
  id?: number,
  name: string
}
export interface CategoryEntity {
  id?: number,
  name: string
}
