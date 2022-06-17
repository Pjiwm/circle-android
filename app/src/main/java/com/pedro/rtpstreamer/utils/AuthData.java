package com.pedro.rtpstreamer.utils;

public class AuthData {
    public AuthClass[] getAuthData() {
        AuthClass[] accounts = new AuthClass[] {
                new AuthClass(
                        "Jop",
                        1,
                        "-----BEGIN RSA PRIVATE KEY-----\n" +
                        "MIIEpAIBAAKCAQEAruztFasZTIJ9HttjRwi72fprEG6J8mcxE7zyrMZQwiVUGHe2\n" +
                        "0x1VZ2ukl2tvXO1lcsAFf12pdQKgK0PxQmUzXYwU0C/+eV3F5ZUUb/DNlvRyGNys\n" +
                        "9wGsNcsUHYbimwz2TecQLacXGgAQlGRqorR58Vw02g8Hi0sqYpAPNPvaOroCTqCA\n" +
                        "GSfMl/+aLGdWEmv3LY6p82KW+gMbjyJwNF9Qi8ZWHq0FivxbJKqpkkCqbLapCq6m\n" +
                        "Eb7Pc3ZNOERwD5boUcqpRJIs9nMw3LUXkHeQV796oC9P94sFUY0B+9DkK6v4lEwA\n" +
                        "YeSGkduxNccWSBL62Zmf+RD977G1a2P1ED+mNwIDAQABAoIBAFwRe9/RxGkOrUhW\n" +
                        "dHRo1maudTea1qRtR9l5s8jYQJ1PBGo0aFfJsq/SajkAZQbmNGUnSD13UL+zeBYN\n" +
                        "lqg5l8V++11wRWIfxw9FXhJlDfCMDJmlXKxNhxRxJM89tXuiYOkz2sIMlg4rRo60\n" +
                        "I6QrlZtByteS3YelMlPaOwbKIFyTt50sApx0uAoYrLS5IpfUtIEjVsymCNNXUA9B\n" +
                        "85oO600MZhluk88f0/MhPqUFHy6cjV4+zG2IeDgMvQNjH/60VJyqQiv9+Xiuho10\n" +
                        "rMUJ4j1aagB3dC6qdR5hSVdwTt9sBsO0Ih+SXfjqr7uPP5ZH75jbzX1G4O+JD1sY\n" +
                        "G9wvG3ECgYEA3w2a/U3esRntisk01tCCsKNqrSLTTpyF0lNHEK6Wk//D4KoMfjAY\n" +
                        "Qtewi199YaYXnK0ohpIBTf9e/3AtFgJa6grZf8NgT7d6uTO7XV6rTaSwdHG8hNh3\n" +
                        "Gy3b1OTxQkCJgl0iKDBWzyGhpPy/Z4pptXo/rl8Wgo34ALOub3DYs3sCgYEAyMNz\n" +
                        "gBbT0CR8nPsadMz7ZYS5VwREX4ZwzrMEiqvtW/4NRV1v4OQl/Zc6fpKuWpq/MuCe\n" +
                        "Ey08PQmZXddKIoKFyKBlMyLSJQmYNxeqq8Tjhm84eJYDYDhWCuCRfPCvSTe/k0P7\n" +
                        "NWVeDv0ZKlKoKIhRhOUyUySJYCBqYKFFKsArLXUCgYAfh5zREaDZCAE8DRUbCH5w\n" +
                        "2ajKsJGOGzIPeRYilYtTgGP/7gm2DLZhPHwIN4dRtljAHPoNi7YDgIW9Ne4pnkub\n" +
                        "qcz08nk6y4TdiSKDIE62uwD2dN1hKfgsL19TyMkRxG2rr96UL8EVpntjBxdHW5l1\n" +
                        "vcIis+5KpibYco3F0v3O4QKBgQCcrCrOowHZRSAaQbt7uxvVNaaNyb6x7XDLXdkF\n" +
                        "fvZU/AXPRAOzNViQdhwn+sAl5O2g/rldrcd8uqj9Gq24ELPmxYU0StBEJyCqncWL\n" +
                        "FrOYjeM795Xa2RwMkQP4xLuGIANZIYxBN0IX4FxrMuYX+4nidBq516F9as6ZakFY\n" +
                        "A2+uoQKBgQCI58HJYoZSHWko/Tg/UzbQOMcTahgkNdfRuboD850oC+rHdMRABI6f\n" +
                        "2oAwWuEC8hQ/LPWnB+69T+1btr+PqPDOHTknCs1rg/Fi/vfrI2RlZkzxwBotqoMZ\n" +
                        "ho1Om3DncxOy6a4k5kLFxttZ9k2FP1ZCVhN4mRxsCx0j9jWhBZy3Qw==\n" +
                        "-----END RSA PRIVATE KEY-----\n",
                        "-----BEGIN PUBLIC KEY-----\n" +
                        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAruztFasZTIJ9HttjRwi7\n" +
                        "2fprEG6J8mcxE7zyrMZQwiVUGHe20x1VZ2ukl2tvXO1lcsAFf12pdQKgK0PxQmUz\n" +
                        "XYwU0C/+eV3F5ZUUb/DNlvRyGNys9wGsNcsUHYbimwz2TecQLacXGgAQlGRqorR5\n" +
                        "8Vw02g8Hi0sqYpAPNPvaOroCTqCAGSfMl/+aLGdWEmv3LY6p82KW+gMbjyJwNF9Q\n" +
                        "i8ZWHq0FivxbJKqpkkCqbLapCq6mEb7Pc3ZNOERwD5boUcqpRJIs9nMw3LUXkHeQ\n" +
                        "V796oC9P94sFUY0B+9DkK6v4lEwAYeSGkduxNccWSBL62Zmf+RD977G1a2P1ED+m\n" +
                        "NwIDAQAB\n" +
                        "-----END PUBLIC KEY-----\n"
                ),
                new AuthClass(
                        "Diego",
                        2,
                        "-----BEGIN RSA PRIVATE KEY-----\n" +
                                "MIIEowIBAAKCAQEA0CqcW6AW9C2syT2otsgkatFJi3RNt8Vga+DrgO5B9yzhI6fo\n" +
                                "GfxzWpdwcgJ/hI4r+QVjTrqCqTrXeqOHIcQcuSCpECPfkPCi2eGtVkp2QILI9gQr\n" +
                                "e9H23aKOVUzrgZ8BNMm5rRgDtqL/tVUjCJyuSEw3v7aLBQBsycpz4EUYxxnPrqLA\n" +
                                "dni3AwDUbE6n15IwkXrHYLqySnavxplqPrPgCfPDkJKggq9b8UeiPUfIcglBuGMn\n" +
                                "UKIQys0CQTo74qU6WSUYfMVTMX51N5an1w0cBB+QeYcArLYvwf5MowaguVvI0588\n" +
                                "nvSvghmu2pjMgt2vfkZ+lgrVwTEQY6+MwMG8wwIDAQABAoIBAGS2KN2fb1uFeZIH\n" +
                                "NcfwvsW7KaTBiRQ+9u5abDX9j4ahfO8+PenMnJaYq/tMwBsGmbmp5KjjTXHImRWp\n" +
                                "Zihs9CSQ65p2cKRz96YILE+SSPHfFJEJ5WvLYm9KRSh5xXr5zaCXdDCNbMaAx8wY\n" +
                                "O5HgE+XAkjw7ebjawsdCHpvDckBomZ5455KcA3NtgQ2lpu4KQ4TOkJTT1F+oKXjX\n" +
                                "bOZZ5zej8TLdqBJRdvKbEtK6bmxewOciWWfLPpuNXduGIV+RdF5ybXERz7odeFw/\n" +
                                "R9lgyjPG9TeGu+3MJPi0X1XwAoLWOCPtyoakC0CYblj9LwUXOnO6XPCufkHeU+iD\n" +
                                "6ft1cckCgYEA9jr7j/MmhAy5OrR01FpTmKgz0AYm4ImCWr9xcfnyLj2DEik/3YOe\n" +
                                "YqQIYy4GHahhpredpPuR8/BiXTrJguJLL+s5la/AWNSRlRQfVsGWS8owAlij+MAU\n" +
                                "1Ed635h7V4BPGBXNCNaa6x/jMqEHvTH6iUftd+LSw6LLTvvsd/JJlCUCgYEA2G0B\n" +
                                "Bxj/tyDvQn6LZkBhteGO0B6Tpe6+0z9kUQC6MHMsb/MoI9PA4NpzqKbqRAIbLnlk\n" +
                                "2g3pBgm0reIVYhaGLF96M80x7VtCrnAO6XlROX1V6p6VBmsVY71KQQ9chrs0FVEz\n" +
                                "/4IvAisSCj3qCNBx/SHsFlz2NlXgdO4rjcqTBMcCgYBHT2WzltO8woKSgdij91Tm\n" +
                                "jTAUOvPfa3OZHICaZazBv4bKJ7AbUxb3slHLh3S1+iHtypeZRFJMt32FSpU5lpf3\n" +
                                "1MPkmfW2xDk1V5KL6ZzBbljhFRLH47kkkV8AioSOHtbJPucWfDB5j86YxxIatPK9\n" +
                                "91rKsaIgjnEa+tLNGho9EQKBgHqWG2vqxAT1WbA/PzQ2qWG9FghaGWV2eo3rLSYY\n" +
                                "lF2XDweGIilQ6sIx2isgCDdnPgAv8Gj2UhQCcV3AXj5F5c/CcNRgmLQJ6hYmELw7\n" +
                                "5D78+CpBksWk5+OrtyBs1ArYhGLCsQI7ueavgLNeaG5/quEnUrNECo2yTMXOlcrl\n" +
                                "gCrHAoGBAMSI+1QC8zFqnqSX+ohxKu4NSYoWe8vmBUqFnw52Zz6AyB7wzJmXzoOV\n" +
                                "UUv0IiLN9CNsNuciLt6JaTivXUiTAFYcpwXRtOzE1jr8Vo91cSLdI7NaK1TSPQOL\n" +
                                "6OjH84+U9LzbcwcFpjVNKwDQNLVKayh117TKFCgl8C0vSitphlMU\n" +
                                "-----END RSA PRIVATE KEY-----\n",
                        "-----BEGIN PUBLIC KEY-----\n" +
                                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0CqcW6AW9C2syT2otsgk\n" +
                                "atFJi3RNt8Vga+DrgO5B9yzhI6foGfxzWpdwcgJ/hI4r+QVjTrqCqTrXeqOHIcQc\n" +
                                "uSCpECPfkPCi2eGtVkp2QILI9gQre9H23aKOVUzrgZ8BNMm5rRgDtqL/tVUjCJyu\n" +
                                "SEw3v7aLBQBsycpz4EUYxxnPrqLAdni3AwDUbE6n15IwkXrHYLqySnavxplqPrPg\n" +
                                "CfPDkJKggq9b8UeiPUfIcglBuGMnUKIQys0CQTo74qU6WSUYfMVTMX51N5an1w0c\n" +
                                "BB+QeYcArLYvwf5MowaguVvI0588nvSvghmu2pjMgt2vfkZ+lgrVwTEQY6+MwMG8\n" +
                                "wwIDAQAB\n" +
                                "-----END PUBLIC KEY-----\n"
                ),
                new AuthClass(
                        "Twan",
                        3,
                        "-----BEGIN RSA PRIVATE KEY-----\n" +
                                "MIIEpQIBAAKCAQEA0Jry/VVG27X89+3mohBrJyjN5ZciLvB3kauuxYs0dhrxnoOT\n" +
                                "9/1u+hYkf9nTYd8TDGB1zKXdpfMM2jENeoA3fJd2jhSJYEj0o7sTP5lBuHoq3pBj\n" +
                                "0K5pbTk+fwAi2ek0T+TmA6KFl+aCXlgnsHnsW9jqK94G4QTvSRjVHwQ57uGukmC+\n" +
                                "Nn6wqSMnKLCyExqhOKycI0OJJ13gDntNXUxoo4Bum0vUJyBQ+4cez0HGaK7kfZ/Y\n" +
                                "rp8s0FR5yhs40gt9A6heOKjiuQtTSCXCXx5R69HyplG9qT8+GRVXOJdGwFn51iqX\n" +
                                "y0UBxCMvoV34ZXGxzGVCPJah2n8Pq8e2pJ7jywIDAQABAoIBAFOkraXSmbTjRemL\n" +
                                "pJx7BNwQym4yqWw001y/gDaJWKG6sAMlMyGOpv7z/fKdvtpRfevKibFjGHq/9/lN\n" +
                                "DeB+pMD6YnkZ1vOajS1Rh6hKpUV5BtNzBfLY3EEFYP+/aDoD/gvC9k+izPpZXRDD\n" +
                                "PDbQzGsPkOXCAvQa7G9c26eyJCCZ99deM3YcS8bP/RkW3csKfBRiUHo+Zmqba6pb\n" +
                                "fOG22AphkDZwTZL5epXb04QYUyG60EHw5H0ZSs8gNadivGu40LTMXS79ZT3q2bAJ\n" +
                                "KmW2+k1CiinsCOqaWXKAakxcjXfl1zus3xOArnShsDAYB6V3HF2hFql+AQNJ6aD4\n" +
                                "T4AD7cECgYEA8AC8kbDFYKq7x/c3qcgpCQ2GVqoKO+Q7vN7nfyvVqOdWqZ+mkQoL\n" +
                                "3tof05RZLHillCmuMeP1tkP912/C+ZlpJuqd+UHAderlhrmktFw8Hu/tNeDvjT+B\n" +
                                "CExuOjIT52zlDAqHiljDWIZAHcsmBAKPr266Z8vKh9/ED58og39bKBECgYEA3oJ2\n" +
                                "ftHUpkwHvs9kCiAJjH07TkFfy7QVZGudof3pdBsSz8HyzusN4k5jU0IehWGq4APQ\n" +
                                "i0fFTnwtIC80ES0Wau4RaKc7dqbACb9In/oGQ4sDvfR+bsZqfKyBbG9AMXnO1iCM\n" +
                                "DdpRyhQ1mIga7RCxKiAfcUyl43NdwqqKRPhhChsCgYEAhGzTn0u7TKBIJojI5Nx1\n" +
                                "j/IveqguJ2fCcm9HqorERIMI9WUK4iJ59EfEodjL71bOfdcVr5+Jlz0i1flryo8U\n" +
                                "Tw3Lhpn6GNY4OUM7lu0Bm0xlRYJMi1JL3wlVFLVG+D3EkAVLTlG1z6WbwRSzcCj9\n" +
                                "oR3mqpjuslQMeVYnY48k/BECgYEA0SLSeTSDqKJBjWikYJfoIbH7MfxkK7Rhfvdf\n" +
                                "O8jJyiVAMyHaM/3eGN0Qokq9ISTBVLiBJMgafUBdsahsSdCVB4AnwLOeQlFMoly1\n" +
                                "yBdaF9XreHj69OdtYy1IoECYl2gjrkL9IHAY+9Su42TZ2gQcYLm8k+M08GmYsMVW\n" +
                                "HKlPnA0CgYEAgSfVR11U0advCkVHpP1hYe2Ww2Gsa1wd2DsUFL+bsx2cQ2eTJjx3\n" +
                                "bxNY239QXuCM9SZI11sqfju96pg0hrQKDzCFFnBTRrA+sXWcSPC9P7dA4CJfG1R2\n" +
                                "sZJrICELZCgGu2iX8cxYrTUQ5fPDTf/zxlE2CQKEP8p90f7GJB9eSCk=\n" +
                                "-----END RSA PRIVATE KEY-----\n",
                        "-----BEGIN PUBLIC KEY-----\n" +
                                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0Jry/VVG27X89+3mohBr\n" +
                                "JyjN5ZciLvB3kauuxYs0dhrxnoOT9/1u+hYkf9nTYd8TDGB1zKXdpfMM2jENeoA3\n" +
                                "fJd2jhSJYEj0o7sTP5lBuHoq3pBj0K5pbTk+fwAi2ek0T+TmA6KFl+aCXlgnsHns\n" +
                                "W9jqK94G4QTvSRjVHwQ57uGukmC+Nn6wqSMnKLCyExqhOKycI0OJJ13gDntNXUxo\n" +
                                "o4Bum0vUJyBQ+4cez0HGaK7kfZ/Yrp8s0FR5yhs40gt9A6heOKjiuQtTSCXCXx5R\n" +
                                "69HyplG9qT8+GRVXOJdGwFn51iqXy0UBxCMvoV34ZXGxzGVCPJah2n8Pq8e2pJ7j\n" +
                                "ywIDAQAB\n" +
                                "-----END PUBLIC KEY-----\n"
                )
        };
        return accounts;
    }

}
