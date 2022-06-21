package com.pedro.rtpstreamer.utils;

public class AuthData {
    public AuthClass[] getAuthData() {
        AuthClass[] accounts = new AuthClass[] {
                new AuthClass(
                        "Jop",
                        "62ac7e873d7a046482fb07db",
                        "62ac7f943d7a046482fb07dd",
                        "-----BEGIN PRIVATE KEY-----\n" +
                                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMoSH49d2zKbU4Yk\n" +
                                "zYdT2GaOkDChjyx8zzi4813ZCWj03PVEDpjCHouJfxwZ9qpDuvw9mavf/fyyKD5A\n" +
                                "2quu3x+KvwrRE7hMYTEgd2qPdL6FKAxs/hCguA8Qf/1cb/Yqvj3lmeLSl870dh7G\n" +
                                "MPVB+hHxYSxvx0NKNi0Io1QJLfdXAgMBAAECgYEAlKC37e+O5q4wxR4UvlEBfo2l\n" +
                                "qN9sO8CheosvFfwpW7WfQBeK1PkTFndPsw+1yo5+3kFA4paibldsGj4BmP3KxkzF\n" +
                                "TrRaGh91j2DFiNmdOYyCexwczjixJhGcYjQSORp0nIE6fYjQGvhmQuBjoiPh2XG+\n" +
                                "7tk6tPkJVQ70r8Q2EiECQQD247AyeUlVK7fPK5xdDygdm6GcPaaT9sFEsyE3iSxd\n" +
                                "icb5xerCoMi81pR6c6udYDRaB7UItvFo92QHvIXMCt5zAkEA0YcLCMWQoKqXz2lI\n" +
                                "PiIaUIrIlviRiF8q5n1ekvWhhWpiihKkfvN/xG/VCTw+c1E1En5R6GIYCVGS+x08\n" +
                                "sqpGjQJAcUtGvcK+jiEDtkPy+xlLdVrHrKT13gxr4BS7d3qxKzNgZZoXAr05oL5v\n" +
                                "YEnYRN2y6GWFA7y/x0VPV+WBGIMcVQJAWkKadkTlcNEMZMUt4dY/D8M5dg/OYbjd\n" +
                                "ej03qIc39YlGucQeKLIssCqc00WogXTSMA1Kq5jWUe1aK+MEatL/YQJAfyUyppFw\n" +
                                "+ajDJNnn2KG+1wN8m41jDTjcxw17cFl85h53SeA86WKanVyCCft1dYF5cvZ3PKCN\n" +
                                "fpg8CFoncrYVCw==\n" +
                                "-----END PRIVATE KEY-----",
                        "-----BEGIN PUBLIC KEY-----\n" +
                                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKEh+PXdsym1OGJM2HU9hmjpAw\n" +
                                "oY8sfM84uPNd2Qlo9Nz1RA6Ywh6LiX8cGfaqQ7r8PZmr3/38sig+QNqrrt8fir8K\n" +
                                "0RO4TGExIHdqj3S+hSgMbP4QoLgPEH/9XG/2Kr495Zni0pfO9HYexjD1QfoR8WEs\n" +
                                "b8dDSjYtCKNUCS33VwIDAQAB\n" +
                                "-----END PUBLIC KEY-----"
                ),
                new AuthClass(
                        "Diego",
                        "2",
                        "2",
                        "-----BEGIN PRIVATE KEY-----\n" +
                                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIOAe5eRDppDQbFu\n" +
                                "rOgqsl+HNVK8OZh/6Z8U54nR8JE6oniketoip/bIGA2BRAPtJ2IhrNf70MgU3z+d\n" +
                                "zwBwAItQMpvHErZk7E86j39UEeSROGcWhduM1Et5/D5QvMCYTQA/LxY/Y/i1irre\n" +
                                "/vTrA0aRmHPLEzLYZo9Y03+k9gJRAgMBAAECgYALCbTWExHztuODvjN7RWI1jIbY\n" +
                                "S/5Vo577gGiy89FKj25lzACX4MLSfxctwwTvg2+D2CnG81ciBEZ884UVl1EkQLTF\n" +
                                "3ezkva9yEl873gExj+JK3ceRKNusqKpwUbuvZaS1vP1HMg33nFgFDhUv11vNQrdR\n" +
                                "wAxo5AxorJ8vboGuFQJBAOsm4Y3C6Fr3irZjEoVgJzilM6Mv88zjNg5ZnvaAXbES\n" +
                                "IL/lN2bJoiNtJug32zH0J0DIhTwMtAV582fJlwz4Wh8CQQCPKRy1zzEuqkXxtRfR\n" +
                                "2yGVRBmGrL8ClyZv6X++8RVbeXKRb+3csMjZ5I02GnOnTG3kfmCajyF+9SNoyPDA\n" +
                                "hPWPAkEAwxdK4jXmWDIvXwnq/8S6RYCpyqWp+z42RsmL3K67DIEhspMU165JWmLg\n" +
                                "eKB+tn79Yf36q8tOP4zhqCXTr+sLuQJALJWHJW/jRz8sDTxysJ5n9Q6CTJjb27iq\n" +
                                "9ctoR1TZa+WEspqDH2nHA2TUPXc7cWyyBKj++ON+cQVzA50grTQ7LQJAQG+LwBhq\n" +
                                "ppWH/2DRSxUtjK5kpyjHloro75nmME8capiScYJ1hTIGLCJAWFqLC4/KKOd6XZOs\n" +
                                "UYITeHEId592SA==\n" +
                                "-----END PRIVATE KEY-----",
                        "-----BEGIN PUBLIC KEY-----\n" +
                                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDgHuXkQ6aQ0GxbqzoKrJfhzVS\n" +
                                "vDmYf+mfFOeJ0fCROqJ4pHraIqf2yBgNgUQD7SdiIazX+9DIFN8/nc8AcACLUDKb\n" +
                                "xxK2ZOxPOo9/VBHkkThnFoXbjNRLefw+ULzAmE0APy8WP2P4tYq63v706wNGkZhz\n" +
                                "yxMy2GaPWNN/pPYCUQIDAQAB\n" +
                                "-----END PUBLIC KEY-----"
                ),
                new AuthClass(
                        "Twan",
                        "3",
                        "3",
                        "-----BEGIN PRIVATE KEY-----\n" +
                                "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIZ/UuXLJOTLvg9X\n" +
                                "rH3d/B9Xgvb95h5Oj+lonweeQWRoLxzP13pS1Oa+nqj6Ffxug4A+VRNKAfwtIb6s\n" +
                                "RHuRoZ0sRjlgzbWSQDwVUUfojWatTRBFBK0Vc2WxokvFeV9FBk/bDad7rr6uKKf/\n" +
                                "Xe2wx2HR7VP2uXJNG6syikRL45nbAgMBAAECgYBq1U4qu88lcfUZSMH6BM8UsjWU\n" +
                                "LZ+RaUQE2hagPYOh1uO9VhbJ0mvMkZODk4rNRZds0ZZQSRQElHDefQW0xbwB7iwd\n" +
                                "G/8VyQu71DYbatANtJ4csG3p9zvHuHuPXvA6DnFT+2E2xfJ4xMyOOrn50VFtcjrm\n" +
                                "HPQ2xXy1YOoowLJzcQJBAMeZ+r/erEeChB7Xa6hATrUa7sPzoVfo6qw+049xvU4X\n" +
                                "c4pN80HJ5A4nKB8MFtLnRAGObZt5oIv0J/WQKbTgWTkCQQCsgBZcsekYYGLubROB\n" +
                                "tsHlmRiL4KsryrNqcNJiXB0XH1FBKKgRny/A9my8N8rmiMeN82M/KavjyYlMc0W6\n" +
                                "ge+zAkEAw09ojVbWPSL5JTSlo31Jhu1TtWov9lVzTBgt667gDgnkhPDBlx52iWLH\n" +
                                "X5n0u2qQoPU9S2U4M7o1X5QxO0xMAQJAM+JS146rjalwGEa2l01hBK4HtArfcUyG\n" +
                                "qn1EylDqbwn8ZpzT8Hjw5Bk6m2IspSp+2zNZpqXiDaGQqm4BixeKAQJBALATKTm0\n" +
                                "abcrhDk95+ZuEVYd8hR9MDsy3Ftq9OKRW/g4mmWXhVpLFazwhImps3ZYs+FokqBe\n" +
                                "zcEhbGgGOtMNDws=\n" +
                                "-----END PRIVATE KEY-----",
                        "-----BEGIN PUBLIC KEY-----\n" +
                                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGf1LlyyTky74PV6x93fwfV4L2\n" +
                                "/eYeTo/paJ8HnkFkaC8cz9d6UtTmvp6o+hX8boOAPlUTSgH8LSG+rER7kaGdLEY5\n" +
                                "YM21kkA8FVFH6I1mrU0QRQStFXNlsaJLxXlfRQZP2w2ne66+riin/13tsMdh0e1T\n" +
                                "9rlyTRurMopES+OZ2wIDAQAB\n" +
                                "-----END PUBLIC KEY-----"
                )
        };
        return accounts;
    }

}
