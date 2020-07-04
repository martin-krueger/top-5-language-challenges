#!/bin/bash


vault write hello_world/issue/localhost common_name="localhost" ttl="24h" > server.txt