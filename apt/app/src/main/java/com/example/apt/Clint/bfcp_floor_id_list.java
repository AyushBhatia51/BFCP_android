package com.example.apt.Clint;

public class bfcp_floor_id_list {
        public short ID;                    // FLOOR-ID
        public bfcp_floor_id_list next;   // Pointer to next FLOOR-ID instance

        public bfcp_floor_id_list(short ID, bfcp_floor_id_list next) {
            this.ID = ID;
            this.next = next;
        }
    }


