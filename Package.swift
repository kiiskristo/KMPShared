// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "MeetAgainShared",
    platforms: [
        .iOS(.v10)
    ],
    products: [
        .library(
            name: "MeetAgainShared",
            targets: ["MeetAgainShared"]),
    ],
    targets: [
        .binaryTarget(
            name: "MeetAgainShared",
            url: "https://github.com/kiiskristo/KMPShared/actions/runs/10035395026/artifacts/1724500665",
            checksum: "123456"
        )
    ]
)